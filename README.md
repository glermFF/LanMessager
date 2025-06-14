# LanMessenger - Chat P2P em Rede Local

**LanMessenger** é um aplicativo de mensagens simples desenvolvido em Java, projetado para funcionar em redes locais sem necessidade de servidores centrais. Ele permite que dispositivos conectados à mesma rede se descubram automaticamente e troquem mensagens via terminal.

---

## 🧠 Objetivo

Este projeto foi criado com foco educacional para estudar o funcionamento de redes de computadores e comunicação via sockets em Java. O objetivo é aplicar conceitos como:

- Comunicação TCP
- Descoberta de peers com UDP
- Threads para comunicação simultânea
- Arquitetura peer-to-peer

---

## 🛠️ Tecnologias

- Java 17^
- Sockets TCP e UDP
- Threads para concorrência
- Execução via terminal (CLI)

---

## 🚀 Funcionalidades

- Envio e recepção de mensagens via TCP
- Descoberta automática de outros usuários na rede via UDP Broadcast
- Interface de terminal simples
- Atualização em tempo real dos peers encontrados

---

## 🔧 Como Executar

1. Compile os arquivos `.java`:
   ```bash
   javac *.java
