# VPNLite

> A desktop application for **configuring VPNs and running network tests** across a two-PC Linux test bench — built in 2017 as an engineering final-year project at ESPIRT in partnership with Sagemcom.

<p align="center">
  <img src="vpnlite-cover.png" alt="VPNLite — original 2017 project cover" width="720">
</p>

<p align="center">
  <em>Original title (FR): « Étude, conception et réalisation d'un portail de configuration et de test de protocoles VPN »</em><br>
  <em>Study, design and implementation of a portal for configuring and testing VPN protocols</em>
</p>

---

## Overview

**VPNLite** is a JavaFX desktop application that lets a network engineer **configure different types of VPN** between two Linux PCs and then **perform different types of tests** against the resulting tunnel — all from a single GUI, without ever dropping into a shell.

The two-PC test bench mirrors a real customer deployment:

- **The Client PC** — a Linux workstation connected to the **Sagemcom gateway** on its LAN side. It plays the role of the roaming/branch user.
- **The Server PC** — a Linux workstation situated on the **WAN**, behind the operator network. It plays the role of the enterprise server the client is trying to reach.

The engineer installs VPNLite on **both PCs**. From the same GUI on either side, the engineer can:

1. **Configure the VPN** on the gateway and on the two endpoints, by walking through a wizard (pick protocol → fill in network parameters → validate → deploy).
2. **Run the tests** needed to validate the tunnel — connectivity (Ping), bandwidth (iPerf3 in both directions), packet capture (Wireshark with protocol-aware filters), etc.
3. **Generate a detailed test report** that the engineer can drop straight into the final report handed to their manager.

VPNLite generates the corresponding shell scripts, pushes them to `/usr/local/bin` on the gateway, brings the tunnel up, runs the test suite, and collects the results — all driven from the application GUI.

The project was built in 2017 by **Souhaieb Marzouk** as a *Projet de Fin d'Études* (engineering final-year project) for the *Diplôme National d'Ingénieur* in Computer Science at **ESPIRT** (École Supérieure Privée d'Ingénierie et de Technologies, Tunisia), in industry partnership with **Sagemcom**.

> ⚠️ **Status: archived.** This repository preserves the original 2017 codebase for reference and retrospective purposes. It is not actively maintained and the dependencies (Java 1.8, JavaFX, NetBeans project files) reflect the 2017 toolchain.

---

## The Two-PC Test Bench

VPNLite is designed around a **two-PC test bench** that reproduces a real customer deployment in the lab. The engineer uses the same VPNLite application on both PCs — one running the client side of the tunnel, the other running the server side.

```
                          Sagemcom Gateway
                          (Linux, root, /usr/local/bin)
                                 │
                ┌────────────────┴───────────────┐
                │                                │
   LAN side ◄───┤                 WAN side ├───► Internet / Operator network
                │                                │
                ▼                                ▼
   ┌────────────────────────┐         ┌────────────────────────┐
   │  CLIENT PC (Linux)     │         │  SERVER PC (Linux)     │
   │  — runs VPNLite        │         │  — runs VPNLite        │
   │  — plays VPN client    │         │  — plays VPN server    │
   │  — local end of tunnel │         │  — remote end of tunnel│
   └────────────────────────┘         └────────────────────────┘
                ▲                                ▲
                │                                │
                └──────────── VPN TUNNEL ────────┘
                  (L2TP/IPSec, PPTP, GRE, …)
```

### Why two PCs?

Because the question *"is the VPN working?"* has two halves:

1. **Client → Server** — can the roaming client reach the enterprise server through the tunnel?
2. **Server → Client** — can the enterprise server push traffic back through the tunnel to the roaming client?

These are not symmetric. Asymmetric routing, NAT, MTU mismatches, and firewall rules routinely break one direction while the other works fine. VPNLite drives the test suite from **both ends** so the engineer doesn't have to.

### Example: bidirectional iPerf3 throughput test

iPerf3 has a strict client/server model: the *client* sends, the *server* listens. To measure throughput in **both** directions of the tunnel, the two PCs must **swap roles** between the two runs:

| Run | iPerf3 client | iPerf3 server | What it measures |
|---|---|---|---|
| **Run 1** | Client PC | Server PC | Throughput from the roaming client to the enterprise server (the typical "user downloads" direction). |
| **Run 2** | Server PC | Client PC | Throughput from the enterprise server back to the roaming client (the "user uploads" / "server pushes data" direction). |

VPNLite orchestrates both runs from the same GUI. The engineer selects the direction, and VPNLite automatically starts the iPerf3 listener on the correct PC, launches the iPerf3 client on the other, waits for the result, and stores it. **The engineer never has to SSH into the Server PC and run `iperf3 -s` manually** — the GUI handles role swapping for them.

This pattern applies to every test VPNLite runs:

- **Ping** — VPNLite swaps source/destination between the two PCs to measure round-trip in both directions.
- **iPerf3** — VPNLite swaps client/server roles (see table above).
- **Traceroute** — VPNLite runs it from each PC so the engineer can compare the forward and reverse paths.
- **Wireshark capture** — VPNLite starts the capture on the right interface on each PC, with the right protocol filter pre-loaded.

---

## Features

### 1. VPN Configuration — Site-to-Site & Client-to-Site

VPNLite supports **two deployment topologies** exposed through a single configuration wizard:

- **Site-to-Site** — connects two remote networks (e.g. two branch offices) through a tunnel established between two gateways. Used for `GRE`, `IPSec` (Tunnel Mode), and `PPTP` server-to-server setups.
- **Client-to-Site** — connects a single roaming client to an enterprise network through a gateway. Used for `L2TP/IPSec`, `L2TP/PPP/IPSec`, `PPPoE/PPP/IP`, `PPPoE/PPP/IPSec`, and `PPTP` client-to-server setups.

The wizard collects all the parameters needed by each protocol stack — WAN IP, public/private router addresses, local IP, remote public IP, remote network CIDR, GRE tunnel IP, etc. — and validates them before any script is generated. An inline **About** panel explains every field inline (e.g. *"Local IP Address: an IP from your network that is not used by any computer or the router"*) so the engineer doesn't need to keep the protocol RFC open in another window.

Because VPNLite is running on **both the Client PC and the Server PC**, the wizard also knows which side needs which piece of configuration. For example, for a PPTP Client-to-Site tunnel, VPNLite pushes `pptpd` server config to the Server PC and `pptp-linux` client config to the Client PC — both from the same form.

### 2. VPN Protocol Stacks Supported

VPNLite ships with configuration templates and generated shell scripts for **six industry-standard VPN stacks**:

| Stack | Topology | Layer | Notes |
|---|---|---|---|
| **L2TP / IPSec** | Client-to-Site | L2 (tunnel) + L3 (crypto) | L2TP encapsulates PPP/IP; IPSec provides the encrypted transport. |
| **L2TP / PPP / IPSec** | Client-to-Site | L2 + L2 + L3 | Explicit PPP session inside L2TP, wrapped in IPSec. |
| **PPPoE / PPP / IP** | Client-to-Site | L2 (Ethernet) + L2 (PPP) + L3 | PPPoE Discovery + PPP session (5 phases), routed over IP. |
| **PPPoE / PPP / IPSec** | Client-to-Site | L2 + L2 + L3 (crypto) | Same as above with IPSec encryption added. |
| **PPTP** | Client-to-Site or Site-to-Site | L2 (tunnel) | Legacy Microsoft Point-to-Point Tunneling Protocol. |
| **GRE** | Site-to-Site | L3 (encapsulation) | Encapsulates multiple protocols; supports discontinuous subnets. |

### 3. Automated Testing — Connectivity, Bandwidth, Capture

Once the tunnel is up, VPNLite drives the standard Linux network testing toolkit from the **same GUI** — and runs every test from **both** the Client PC and the Server PC so the engineer gets bidirectional results without having to swap roles by hand.

| Test | What it measures | How VPNLite handles direction |
|---|---|---|
| **Ping** (ICMP / UDP / TCP) | Round-trip latency and packet loss through the tunnel | Runs from Client → Server **and** from Server → Client, stores both. |
| **iPerf3 throughput** | TCP/UDP bandwidth through the tunnel | Swaps iPerf3 client/server roles between the two PCs so both directions are measured (see the table in *The Two-PC Test Bench* above). |
| **iPerf3 with selectable options** | Port, role, duration, link speed (100 Mbps / 1000 Mbps Full Duplex) | All exposed as form fields; no need to memorise the `iperf3` CLI flags. |
| **Traceroute** | Forward and reverse path through the tunnel | Runs from both PCs. |
| **Wireshark capture** | Per-protocol packet inspection on the tunnel interface | VPNLite pre-loads the right capture filter per protocol (`gre`, `ipsec`, `pptp`, …) on each PC. |
| **Tool inventory** | Which Linux tools are installed vs. missing on each PC | The *Packages Installation* page probes both PCs and shows e.g. `iPerf 3 Installed`, `NMAP Uninstalled`, `Wireshark Installed`. Missing tools can be installed from the same screen. |

### 4. Report Generation

After the VPN is configured and the test suite has run, VPNLite **generates a detailed test report** consolidating all the results collected on both PCs — Ping latencies in both directions, iPerf3 throughput in both directions, traceroute paths, Wireshark capture summaries, and the parameters that were used to bring up the tunnel.

The engineer can:

- **Export** the report in a portable format (e.g. PDF / HTML / CSV, depending on the export target selected in the UI).
- **Drop it straight into their final report** to their manager — no manual copy-paste from terminal output, no reformatting of `iperf3` logs, no hand-drawn tables.

This was the original business case for the project: Sagemcom engineers were spending too much time writing up VPN test reports by hand after each validation campaign. VPNLite automates the writeup so the engineer can focus on the engineering, not the paperwork.

### 5. End-to-End Engineer Workflow

The UI is structured around a workflow that mirrors how a field engineer actually works:

1. **Verify installed / uninstalled software** on both the Client PC and the Server PC.
2. **Install / uninstall** the required packages (`ipsec`, `l2tp`, `ppp`, `pppoe`, `pptp`, `iperf3`, `wireshark`, `nmap`, `ethtool`, `traceroute`, …) — from the GUI.
3. **Configure the VPN** via the wizard (VPN protocol → network parameters → validate). VPNLite pushes the right config to the gateway, to the Client PC, and to the Server PC.
4. **Stop / tear down** the VPN configuration if something needs to change — and redeploy in seconds.
5. **Launch tests** (connectivity, bandwidth, capture) — VPNLite runs each test in both directions automatically.
6. **Generate the report** — VPNLite consolidates every result into a single document the engineer can attach to their final report to their manager.

---

## Architecture

### Physical Topology

```
   ┌────────────────────────┐                      ┌────────────────────────┐
   │  CLIENT PC (Linux)     │                      │  SERVER PC (Linux)     │
   │  runs VPNLite          │                      │  runs VPNLite          │
   │  plays VPN client      │                      │  plays VPN server      │
   └───────────┬────────────┘                      └────────────┬───────────┘
               │ LAN                                             │ WAN
               │                                                 │
               ▼                                                 ▼
        ┌──────────────────────────────────────────────────────────────┐
        │                  Sagemcom Gateway (Linux)                   │
        │                       root / sudo                           │
        │             generated scripts in /usr/local/bin             │
        └──────────────────────────────┬───────────────────────────────┘
                                       │
                                       ▼
                              ┌─────────────────┐
                              │   Internet /    │
                              │  Operator WAN   │
                              └─────────────────┘
```

The Sagemcom gateway sits between the Client PC (on its LAN side) and the Server PC (on the WAN). It runs Linux and is the device on which VPNLite deploys the generated shell scripts.

The engineer installs VPNLite on **both the Client PC and the Server PC**. The two instances coordinate (each one manages its own local end of the tunnel + its own local test runs), so the engineer can drive the whole campaign from either side.

### How VPNLite Generates & Deploys Scripts

VPNLite is fundamentally a **shell-script generator and orchestrator**. When the engineer clicks *Validate* in the configuration wizard, the application:

1. Collects every form field into a parameter set.
2. Selects the appropriate protocol template (`gre.sh`, `pptp.sh`, `l2tp-ipsec.sh`, …).
3. Substitutes the parameters into the template to produce a self-contained `#!/bin/sh` script (`set -ef`, `getopts`, case-based dispatch — classic sh dialect so it runs on busybox-Alpine or minimal Sagemcom gateway images).
4. Writes the script to `/usr/local/bin/` on the gateway (and the matching client/server script on the corresponding PC).
5. Runs the script as root, bringing the tunnel up.
6. Optionally launches the test suite — running each test on the Client PC and the Server PC in turn, with Wireshark pre-loaded with the right capture filter for the active protocol.

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

This is why Wireshark capture filters are protocol-specific: `gre` for GRE tunnels, `ipsec` for IPSec, `pptp` for PPTP, etc. VPNLite pre-loads the right filter for the active configuration on each PC.

---

## Tech Stack

| Layer | Technology |
|---|---|
| **Language** | Java 1.8+ |
| **GUI framework** | JavaFX |
| **IDE** | NetBeans |
| **Target OS** | Linux (Client PC, Server PC, and Sagemcom gateway) |
| **Privilege mode** | Root / Administrator (required for `/usr/local/bin` writes and tunnel bring-up) |
| **Generated scripts** | POSIX shell (`#!/bin/sh` — runs on busybox/Alpine/minimal gateway images) |
| **External Linux tools orchestrated by the app** | `ipsec` / `strongSwan`, `xl2tpd`, `pppd`, `pppoe`, `pptpd` / `pptp-linux`, `iperf3`, `wireshark` / `tshark`, `nmap`, `ethtool`, `traceroute`, `ftp`, `snmp`, `dnsutils` |

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
- **Two Linux PCs** (or two Linux VMs on the same host):
  - **Client PC** — connected to the Sagemcom gateway on its LAN side.
  - **Server PC** — connected on the WAN side.
- **A Sagemcom gateway** running BusyBox-based Linux (or any Linux router you want to use as the tunnel endpoint).
- **Root / sudo access** on both PCs and on the gateway — required for writing to `/usr/local/bin` and for bringing up tunnels.
- **Linux networking tools** that VPNLite orchestrates (installed automatically via the *Packages Installation* page):
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

### 3. Set up the two-PC test bench

1. Connect the **Client PC** to the LAN side of the Sagemcom gateway.
2. Connect the **Server PC** to the WAN side (e.g. behind another router that simulates the operator network).
3. Make sure both PCs can reach the gateway and (once a tunnel is up) can reach each other through the tunnel.

### 4. Run VPNLite on both PCs

VPNLite needs to run as root (or with sufficient privileges to write `/usr/local/bin` and bring interfaces up). Run it on **both the Client PC and the Server PC**:

```bash
sudo java -jar dist/VPNLite.jar
```

### 5. Drive the wizard end-to-end

1. **Main Page → Packages Installation** (on both PCs) — verify all required Linux tools are installed. Install the missing ones.
2. **Main Page → VPN Configuration** — pick a protocol, fill in the network parameters, click **Validate**. VPNLite writes the shell script to `/usr/local/bin/` on the gateway and the matching client/server script on each PC.
3. **Main Page → Networking Tests** — run Ping / iPerf3 / Wireshark against the live tunnel. VPNLite runs each test in **both** directions (Client → Server and Server → Client) automatically.
4. **Main Page → Report** — generate the detailed test report and export it for your final writeup to your manager.

<p align="center">
  <img src="vpnlite-pptp-config-ui.png" alt="VPNLite — PPTP configuration wizard with topology diagram" width="720">
</p>

<p align="center"><em>The PPTP configuration wizard with inline topology diagram and validation panel.</em></p>

---

## Screenshots

| Screen | Description |
|---|---|
| Main Page | Entry menu — Packages Installation, VPN Configuration, Networking Tests, Report, Gateway Information. |
| Packages Installation | Probes both PCs and shows install status for iPerf3, Wireshark, NMAP, ethtool, traceroute, FTP, SNMP, DNSUtils. Install/uninstall from the UI. |
| VPN Configuration Wizard | Protocol selector (GRE / PPTP / L2TP-IPSec / L2TP-PPP-IPSec / PPPoE-PPP-IP / PPPoE-PPP-IPSec), WAN IP, West-Site / East-Site parameters, inline topology diagram. Buttons: Stop / Validate / Back / Next. |
| About Panel | Per-protocol help text explaining every field (e.g. for GRE: "Local IP Address: an IP from your network that is not used by any computer or the router"). |
| Networking Tests | Ping (UDP/TCP), iPerf3 (port, client/server, duration, 100/1000 Full Duplex), Wireshark capture with protocol-specific filters. Each test can be launched in either direction; VPNLite handles role-swapping between the Client PC and the Server PC. |
| Report | Consolidates every test result (both directions) plus the tunnel parameters into a single exportable document the engineer can attach to their final report. |
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

- **JavaFX → web frontend.** A JavaFX desktop app tied the operator to a workstation with a JVM; a small web UI (React + a Go/Rust backend) would let the engineer configure the bench from any browser. The shell-script generation logic itself stays the same.
- **Template engine instead of string concatenation.** The 2017 version built shell scripts with manual `String.replace(...)`. A proper template engine (Thymeleaf, Mustache, Jinja-equivalent) would make the templates reviewable and testable in isolation.
- **Idempotent deployment.** The original scripts were not strictly idempotent — re-running them could leave stale rules behind. Today I'd generate `systemd` units or use a config-management tool (Ansible) for declarative, idempotent deployment.
- **WireGuard.** WireGuard was merged into the Linux kernel in 2020 and would replace most of the L2TP/IPSec and PPTP stacks with a single, simpler, faster protocol. PPTP in particular is now considered broken — its inclusion here is purely historical.
- **Automated end-to-end tests for the app itself.** The 2017 version tested *VPN tunnels* but had no automated tests for the GUI or the script generator. Today I'd add TestFX for the UI and golden-file tests for the generated scripts.

That said, the core design — *run on both ends of the tunnel, generate a shell script from a structured form, deploy it, run tests in both directions, export a single report* — still holds up. It's the same pattern Ansible, cloud-init, and modern network-automation tools use today, just packaged as a desktop app.

---

## Limitations

- **No longer maintained.** The codebase targets Java 1.8 and the 2017 Linux networking ecosystem. Some dependencies may not build cleanly on modern distros.
- **Linux-only.** The generated scripts are POSIX `sh` and rely on Linux networking tools; there is no Windows or macOS support.
- **Two-PC bench required.** The application is designed to run on both the Client PC and the Server PC. Running it on only one PC limits the engineer to one-directional tests.
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
