# Suporte SENAI-SP

Aplicacao Spring Boot para gerenciamento de chamados. A demonstracao publica esta em `docs/` e funciona no GitHub Pages como uma interface estatica, salvando os chamados no `localStorage` do navegador.

## GitHub Pages

No repositorio do GitHub, abra **Settings > Pages** e selecione **Deploy from a branch**, a branch principal e a pasta **/(root)**. O `index.html` da raiz encaminha para a demonstracao em `docs/`.

O GitHub Pages nao executa Java, Spring Boot, autenticacao ou banco de dados. Para usar esses recursos, execute a aplicacao Spring em um servidor com Java 21 e banco configurado. A demonstracao publica e destinada a apresentacao visual e testes no navegador.