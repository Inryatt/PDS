1) Problema
Quatro amigos querem jogar o seu jogo preferido em conjunto num servidor LAN local, porém não se encontram na mesma rede.
Para isto decidiram utilizar um VPN!

Pada poderem jogar, têm de abrir o Mundo. Este só pode ser aberto se o Jogo estiver ligado. Depois, têm de abrir o Mundo para LAN,
e isto só pode ser feito se a VPN estiver ativa. Se Mundo, Jogo e VPN forem classes vamos ter high-coupling entre elas!

2) Solução
Para resolvermos este problema, os amigos decidiram criar um script para coordenar as operações e poderem trocar de Jogo
e de Mundo sem terem de reconfigurar tudo o resto.
Aqui, vamos criar a classe Mediator para reduzir o coupling

3) Fontes
https://www.baeldung.com/java-mediator-pattern (adaptado)