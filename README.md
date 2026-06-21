# VPNLite

> A desktop application for **configuring and testing VPN protocols** on Linux gateways — built in 2017 as an engineering final-year project at ESPIRT in partnership with Sagemcom.

<p align="center">
  <img src="vpnlite-cover.png" alt="VPNLite — original 2017 project cover" width="720">
</p>

<p align="center">
  <em>Original title (FR): « Étude, conception et réalisation d'un portail de configuration et de test de protocoles VPN »</em><br>
  <em>Study, design and implementation of a portal for configuring and testing VPN protocols</em>
</p>

---

## Overview

**VPNLite** is a JavaFX desktop application that lets a network engineer configure a VPN end-to-end on a Linux gateway (Sagemcom gateway in the original deployment context) and then immediately run automated connectivity and bandwidth tests against the tunnel — all from a single GUI, without dropping into a shell.

Instead of writing and debugging long shell scripts by hand, the operator walks through a wizard (VPN protocol → network parameters → validate → deploy). VPNLite then generates the corresponding shell scripts, pushes them to `/usr/local/bin` on the gateway, brings the tunnel up, and runs the test suite (Ping, iPerf3, traceroute, Wireshark captures, etc.). When something fails, the operator can stop the configuration, edit the parameters, and redeploy in seconds.

The project was built in 2017 by **Souhaieb Marzouk** as a *Projet de Fin d'Études* (engineering final-year project) for the *Diplôme National d'Ingénieur* in Computer Science at **ESPIRT** (École Supérieure Privée d'Ingénierie et de Technologies, Tunisia), in industry partnership with **Sagemcom**.

> ⚠️ **Status: archived.** This repository preserves the original 2017 codebase for reference and retrospective purposes. It is not actively maintained and the dependencies (Java 1.8, JavaFX, NetBeans project files) reflect the 2017 toolchain.

---

## Features

### 1. VPN Configuration — Site-to-Site & Client-to-Site

VPNLite supports **two deployment topologies** exposed through a single configuration wizard:

- **Site-to-Site** — connects two remote networks (e.g. two branch offices) through a tunnel established between two gateways. Used for `GRE`, `IPSec` (Tunnel Mode), and `PPTP` server-to-server setups.
- **Client-to-Site** — connects a single roaming client to an enterprise network through a gateway. Used for `L2TP/IPSec`, `L2TP/PPP/IPSec`, `PPPoE/PPP/IP`, `PPPoE/PPP/IPSec`, and `PPTP` client-to-server setups.

The wizard collects all the parameters needed by each protocol stack — WAN IP, public/private router addresses, local IP, remote public IP, remote network CIDR, GRE tunnel IP, etc. — and validates them before any script is generated. An inline **About** panel explains every field inline (e.g. *"Local IP Address: an IP from your network that is not used by any computer or the router"*) so the operator doesn't need to keep the protocol RFC open in another window.

### 2. Automated Testing — Connectivity, Bandwidth & More

Once a tunnel is up, VPNLite drives the standard Linux network testing toolkit from the same UI:

- **Connectivity testing** — Ping (UDP/TCP), traceroute.
- **Bandwidth testing** — iPerf3 with selectable port, role (Client/Server), duration, and link speed (100 Mbps / 1000 Mbps, Full Duplex).
- **Packet capture** — Wireshark filters pre-loaded per protocol (e.g. `gre` for Site-to-Site GRE tunnels), so the operator can capture before bringing the tunnel up.
- **Tool inventory** — the *Packages Installation* page probes the gateway and reports which tools are installed vs. missing (e.g. `iPerf 3 Installed`, `NMAP Uninstalled`, `Wireshark Installed`). Missing tools can be installed from the same screen.

### 3. Six VPN Protocol Stacks

VPNLite ships with configuration templates and generated shell scripts for six industry-standard VPN stacks:

| Stack | Topology | Layer | Notes |
|---|---|---|---|
| **L2TP / IPSec** | Client-to-Site | L2 (tunnel) + L3 (crypto) | L2TP encapsulates PPP/IP; IPSec provides the encrypted transport. |
| **L2TP / PPP / IPSec** | Client-to-Site | L2 + L2 + L3 | Explicit PPP session inside L2TP, wrapped in IPSec. |
| **PPPoE / PPP / IP** | Client-to-Site | L2 (Ethernet) + L2 (PPP) + L3 | PPPoE Discovery + PPP session (5 phases), routed over IP. |
| **PPPoE / PPP / IPSec** | Client-to-Site | L2 + L2 + L3 (crypto) | Same as above with IPSec encryption added. |
| **PPTP** | Client-to-Site or Site-to-Site | L2 (tunnel) | Legacy Microsoft Point-to-Point Tunneling Protocol. |
| **GRE** | Site-to-Site | L3 (encapsulation) | Encapsulates multiple protocols; supports discontinuous subnets. |

### 4. Operator Workflow

The UI is structured around a six-step workflow that mirrors how a field engineer actually works:

1. **Verify installed/uninstalled software** on the gateway.
2. **Install / uninstall** the required packages (`ipsec`, `l2tp`, `ppp`, `pppoe`, `pptp`, `iperf3`, `wireshark`, `nmap`, `ethtool`, `traceroute`, …).
3. **Configure the VPN** via the wizard (VPN protocol → network parameters → validate).
4. **Stop / tear down** the VPN configuration if something needs to change.
5. **Launch tests** (connectivity, bandwidth, capture).
6. **Export results** for reporting.

---

## Architecture

### Physical Topology

```
┌────────────────┐         ┌─────────────┐         ┌──────────────┐
│  Client LAN    │ ─────── │  Passerelle │ ─────── │   Internet   │ ─────── ┌──────────────┐
│  (workstations)│         │  (Gateway)  │         │              │         │  Server WAN  │
└────────────────┘         └─────────────┘         └──────────────┘         └──────────────┘
       10.1.101.0/24            │                                                 192.168.0.0/24
                                 │
                          runs VPNLite + shell scripts in /usr/local/bin
```

The gateway runs Linux and is the device on which VPNLite deploys the generated shell scripts. The desktop running the VPNLite GUI connects to the gateway (or runs directly on it, in root/administrator mode) and orchestrates both configuration and testing.

### How VPNLite Generates & Deploys Scripts

VPNLite is fundamentally a **shell-script generator and orchestrator**. When the operator clicks *Validate* in the configuration wizard, the application:

1. Collects every form field into a parameter set.
2. Selects the appropriate protocol template (`gre.sh`, `pptp.sh`, `l2tp-ipsec.sh`, …).
3. Substitutes the parameters into the template to produce a self-contained `#!/bin/sh` script (`set -ef`, `getopts`, case-based dispatch — classic sh dialect so it runs on busybox-Alpine or minimal Sagemcom gateway images).
4. Writes the script to `/usr/local/bin/` on the gateway.
5. Runs the script as root, bringing the tunnel up.
6. Optionally launches the test suite and Wireshark with the right capture filter pre-applied.

<p align="center">
  <img src="vpnlite-gre-config-ui.png" alt="VPNLite — GRE Site-to-Site configuration wizard" width="720">
</p>

<p align="center"><em>The GRE configuration wizard showing the West-Site / East-Site network topology and the inline validation panel.</em></p>

### GRE Packet Structure (reference)

For Site-to-Site GRE tunnels, the encapsulated packet on the wire looks like:

```
┌────────────────────┬───────────┬────────────┬────────────┬───────────┬────────────┬──────┐
│ PPP Delivery Hdr   │ IP Header │ GRE Header │ PPP Header │ IP Header │ TCP Header │ Data │
└────────────────────┴───────────┴────────────┴────────────┴───────────┴────────────┴──────┘
|<────────────── delivered over the GRE tunnel ──────────────────────────────────────────>|
```

This is why Wireshark capture filters are protocol-specific: `gre` for GRE tunnels, `ipsec` for IPSec, `pptp` for PPTP, etc. VPNLite pre-loads the right filter for the active configuration.

---

## Tech Stack

| Layer | Technology |
|---|---|
| **Language** | Java 1.8+ |
| **GUI framework** | JavaFX |
| **IDE** | NetBeans |
| **Target OS** | Linux (gateway side) |
| **Privilege mode** | Root / Administrator (required for `/usr/local/bin` writes and tunnel bring-up) |
| **Generated scripts** | POSIX shell (`#!/bin/sh` — runs on busybox/Alpine/minimal gateway images) |
| **External Linux tools orchestrated by the app** | `ipsec` / `strongSwan`, `xl2tpd`, `pppd`, `pppoe`, `pptpd` / `pptp-client`, `iperf3`, `wireshark` / `tshark`, `nmap`, `ethtool`, `traceroute`, `ftp`, `snmp`, `dnsutils` |

---

## Project Structure

```
VPNLite/
├── nbproject/
├── src/
│   └── vpnlite/
│       ├── VPNLite.java
│       ├── controllers/
│       │   ├── SoftwareOverviewController.java
│       │   ├── PrincipalOverviewController.java
│       │   ├── VPNOverviewController.java
│       │   ├── NetworkingTestsOverviewController.java
│       │   └── UtilisateurOverviewController.java
│       ├── scripts/
│       │   ├── FTPTest.sh
│       │   ├── CurlTest.sh
│       │   ├── CurlResult.sh
│       │   ├── killprocess.sh
│       │   ├── WGETTest.sh
│       │   ├── L2TPPPPIPSecSTunnel.sh
│       │   ├── L2TPIPSecSTunnel.sh
│       │   ├── L2TPIPSecSTransport.sh
│       │   ├── L2TPPPPIPSecSTransport.sh
│       │   ├── PPPoEPPPIPClient.sh
│       │   ├── L2TPIPSecCTunnel.sh
│       │   ├── L2TPPPPIPSecCTunnel.sh
│       │   ├── L2TPPPPIPSecCTransport.sh
│       │   ├── L2TPIPSecCTransport.sh
│       │   ├── L2TPIPSecSTunnelS.sh
│       │   ├── RouteAdd.sh
│       │   ├── GWYIPAddress.sh
│       │   ├── PPPoEPPPIPServer.sh
│       │   ├── L2TPIPSecSTunnelC.sh
│       │   ├── GREClientStop.sh
│       │   ├── GREServerStop.sh
│       │   ├── PPTPServerStop.sh
│       │   ├── PPTPClientStop.sh
│       │   ├── Wireshark.sh
│       │   ├── InterfaceName.sh
│       │   ├── DelPPPoE.sh
│       │   ├── InstallPPPoE.sh
│       │   ├── IPerf3Server.sh
│       │   ├── IPerf3ClientUDP.sh
│       │   ├── ResultToExcel.sh
│       │   ├── IPerf3ClientTCP.sh
│       │   ├── PingResultTest.sh
│       │   ├── L2TPIPSecClientStop.sh
│       │   ├── PPTPServer.sh
│       │   ├── GRETunnelC.sh
│       │   ├── GRETunnelS.sh
│       │   ├── PPTPClient.sh
│       │   ├── ethpool.sh
│       │   ├── InterfacePPP0.sh
│       │   ├── remoteippptp.sh
│       │   ├── L2TPIPSecServerStop.sh
│       │   ├── InstallStrongswan.sh
│       │   ├── UPDATE.sh
│       │   ├── DelSNMP.sh
│       │   ├── DelStrongswan.sh
│       │   ├── TRResultTest.sh
│       │   ├── PingTest.sh
│       │   ├── pptpd-options
│       │   ├── ExternalIPAddress.sh
│       │   ├── InternalIPAddress.sh
│       │   ├── CheckProgram.sh
│       │   ├── OsRelease.sh
│       │   └── TracerouteTest.sh
│       ├── css/
│       │   ├── vpnoverview.css
│       │   ├── utilisateuroverview.css
│       │   ├── principaloverview.css
│       │   ├── networkingtestsoverview.css
│       │   └── softwareoverview.css
│       └── views/
├── libs/
├── README.md
└── LICENSE
```

> ℹ️ The structure above reflects the typical NetBeans + JavaFX layout from 2017 and is provided as a guide. If your local copy differs (e.g. Maven `pom.xml` instead of `build.xml`, or a flat `src/` tree), adjust this section to match the actual repository layout before pushing to GitHub.

---

## Prerequisites

To build and run VPNLite today you'll need to recreate the 2017 toolchain:

- **JDK 8** (OpenJDK 8 or Oracle JDK 8uXXXX). JavaFX is bundled with JDK 8 — later JDKs removed JavaFX from the core distribution and would require OpenJFX as a separate module.
- **NetBeans IDE 8.x** (or any IDE that can open a classic NetBeans `nbproject/` Ant project). IntelliJ IDEA and Eclipse can also import the project, but you'll lose the NetBeans-specific GUI builder (.fxml + controller wiring still works).
- **A Linux machine** to act as the gateway (or a Linux VM). VPNLite was developed and tested against Sagemcom gateways running a BusyBox-based Linux.
- **Root / sudo access** on the gateway — required for writing to `/usr/local/bin` and for bringing up tunnels.
- **Linux networking tools** that VPNLite orchestrates:
  - `ipsec` / strongSwan
  - `xl2tpd`, `pppd`, `pppoe`, `pptpd` (server) and `pptp-linux` (client)
  - `iperf3`, `wireshark` (or `tshark`), `nmap`, `ethtool`, `traceroute`

> ⚠️ **Modern Linux note (2025+).** Some of these packages have been renamed, deprecated, or absorbed into other projects on recent distros. PPTP is considered cryptographically broken — do not deploy it in production. IPSec via `strongSwan` and WireGuard are the modern equivalents. VPNLite is preserved here as a 2017 artifact, not as a recommended production tool.

---

## Getting Started

### 1. Clone & open the project

```bash
git clone https://github.com/<YOUR_USERNAME>/VPNLite.git
cd VPNLite
```

Open the `nbproject/` folder in NetBeans 8.x, or import the `build.xml` as an Ant project in IntelliJ/Eclipse.

### 2. Build

From NetBeans: **Run → Clean and Build Project**. From the command line:

```bash
ant clean jar
# produces dist/VPNLite.jar
```

### 3. Run

VPNLite needs to run as root on the gateway (or with sufficient privileges to write `/usr/local/bin` and bring interfaces up):

```bash
sudo java -jar dist/VPNLite.jar
```

### 4. Use the wizard

1. **Main Page → Packages Installation** — verify all required Linux tools are installed. Install the missing ones.
2. **Main Page → VPN Configuration** — pick a protocol, fill in the network parameters, click **Validate**. VPNLite writes the shell script to `/usr/local/bin/` on the gateway.
3. **Main Page → Networking Tests** — run Ping / iPerf3 / Wireshark against the live tunnel.
4. **Export results** for your test report.

<p align="center">
  <img src="vpnlite-pptp-config-ui.png" alt="VPNLite — PPTP configuration wizard with topology diagram" width="720">
</p>

<p align="center"><em>The PPTP configuration wizard with inline topology diagram and validation panel.</em></p>

---

## Screenshots

| Screen | Description |
|---|---|
| Main Page | Entry menu — Packages Installation, VPN Configuration, Networking Tests, Gateway Information. |
| Packages Installation | Probes the gateway and shows install status for iPerf3, Wireshark, NMAP, ethtool, traceroute, FTP, SNMP, DNSUtils. Install/uninstall from the UI. |
| VPN Configuration Wizard | Protocol selector (GRE / PPTP / L2TP-IPSec / L2TP-PPP-IPSec / PPPoE-PPP-IP / PPPoE-PPP-IPSec), WAN IP, West-Site / East-Site parameters, inline topology diagram. Buttons: Stop / Validate / Back / Next. |
| About Panel | Per-protocol help text explaining every field (e.g. for GRE: "Local IP Address: an IP from your network that is not used by any computer or the router"). |
| Networking Tests | Ping (UDP/TCP), iPerf3 (port, client/server, duration, 100/1000 Full Duplex), Wireshark capture with protocol-specific filters. |
| Gateway Information | Tester info, Software/HW version, Serial Number, Gateway IP, MAC Address, Configuration File Browse/Import, Initialize/Save. |

---

## Academic Context

This project was the *Projet de Fin d'Études* (engineering final-year project) submitted in 2017 in partial fulfilment of the *Diplôme National d'Ingénieur* in Computer Science at **ESPIRT — École Supérieure Privée d'Ingénierie et de Technologies** (Tunisia), in industry partnership with **Sagemcom**.

| Role | Name |
|---|---|
| **Student / Author** | Souhaieb Marzouk |
| **Industry supervisor** (Sagemcom) | M. Chouchene Sofiene |
| **Academic supervisor** (ESPIRT) | Mme. Sayah Salma |

The original project defense presentation (*« Étude, conception et réalisation d'un portail de configuration et de test de protocoles VPN »*) is preserved alongside this repository as `VPNLite Presentation by Souhaieb Marzouk.pdf`.

---

## Retrospective (looking back from 2025)

Building VPNLite in 2017 was a formative experience. A few things I'd do differently today:

- **JavaFX → web frontend.** A JavaFX desktop app tied the operator to a workstation with a JVM; a small web UI (React + a Go/Rust backend) would let the operator configure the gateway from any browser. The shell-script generation logic itself stays the same.
- **Template engine instead of string concatenation.** The 2017 version built shell scripts with manual `String.replace(...)`. A proper template engine (Thymeleaf, Mustache, Jinja-equivalent) would make the templates reviewable and testable in isolation.
- **Idempotent deployment.** The original scripts were not strictly idempotent — re-running them could leave stale rules behind. Today I'd generate `systemd` units or use a config-management tool (Ansible) for declarative, idempotent deployment.
- **WireGuard.** WireGuard was merged into the Linux kernel in 2020 and would replace most of the L2TP/IPSec and PPTP stacks with a single, simpler, faster protocol. PPTP in particular is now considered broken — its inclusion here is purely historical.
- **Automated end-to-end tests for the app itself.** The 2017 version tested *VPN tunnels* but had no automated tests for the GUI or the script generator. Today I'd add TestFX for the UI and golden-file tests for the generated scripts.

That said, the core design — *generate a shell script from a structured form, deploy it, run tests, export results* — still holds up. It's the same pattern Ansible, cloud-init, and modern network-automation tools use today, just packaged as a desktop app.

---

## Limitations

- **No longer maintained.** The codebase targets Java 1.8 and the 2017 Linux networking ecosystem. Some dependencies may not build cleanly on modern distros.
- **Linux-only.** The generated scripts are POSIX `sh` and rely on Linux networking tools; there is no Windows or macOS gateway support.
- **PPTP included for historical completeness.** Do not deploy PPTP in production in 2025 — it is cryptographically broken ([CVE-1999-0434](https://nvd.nist.gov/vuln/detail/CVE-1999-0434) and many successors). Use IPSec, WireGuard, or OpenVPN instead.
- **No encryption at the application layer.** VPNLite orchestrates VPN protocols; it does not implement them from scratch. Security depends entirely on the underlying Linux tools being correctly configured.
- **Single-gateway scope.** The 2017 version managed one gateway at a time. Multi-gateway fleet management is out of scope.

---

## License

This project is released under the **MIT License** — see [LICENSE](LICENSE) for the full text.

The original 2017 project defense presentation (`VPNLite Presentation by Souhaieb Marzouk.pdf`) is © Souhaieb Marzouk and is included in this repository for archival and reference purposes.

---

## Acknowledgements

- **Sagemcom** — for hosting the project, providing the gateway hardware, and the industry supervision.
- **ESPIRT** — École Supérieure Privée d'Ingénierie et de Technologies — for the academic framework and supervision.
- The maintainers of the open-source Linux networking tools that VPNLite orchestrates: `strongSwan`, `xl2tpd`, `pppd`, `pppoe`, `pptpd`, `iperf3`, `Wireshark`, `nmap`, `ethtool`, `traceroute`.
