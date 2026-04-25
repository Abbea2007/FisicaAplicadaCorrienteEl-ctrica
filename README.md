⚡ Circuitos de Corriente Eléctrica

Este repositorio contiene conceptos fundamentales sobre circuitos eléctricos, incluyendo:

Magnitudes físicas básicas
Leyes fundamentales
Tipos de conexiones
Análisis de resistores y capacitores
Implementación en Java
👥 Integrantes
Carlos Alfredo Abea Martinez
Andrea Johanna Duarte Guerrero
Solieth Valentina Trejos Perez

📊 Magnitudes Eléctricas

| Magnitud                | Unidad de Medida | Abreviación |
| ----------------------- | ---------------- | ----------- |
| Intensidad de corriente | Amperio          | A           |
| Voltaje                 | Voltio           | V           |
| Resistencia             | Ohmio            | Ω           |
| Capacitancia            | Faradio          | F           |
| Potencia                | Watt             | W           |

⚖️ Convención de Signos
La corriente que entra a un elemento se considera positiva (+)
La corriente que sale se considera negativa (−)

⚡ Ley de Ohm

La ley fundamental de los circuitos eléctricos establece:
V = I * R
Donde:

V = Voltaje (Voltios)
I = Corriente (Amperios)
R = Resistencia (Ohmios)

📌 Se aplica principalmente a resistores.

🔋 Capacitores

Los capacitores almacenan energía en forma de campo eléctrico.

La relación fundamental es:
Q = C * V

Donde:

Q = Carga (Coulombs)
C = Capacitancia (Faradios)
V = Voltaje (Voltios)

🔗 Tipos de Circuitos
🔸 Circuitos en Serie
Los elementos están conectados uno tras otro
La corriente es la misma en todos los componentes
El voltaje se divide entre los elementos

📌 Fórmulas:
Resistores:
Req = R1 + R2 + R3 + ...

Capacitores:
1/Ceq = 1/C1 + 1/C2 + 1/C3 + ...

🔸 Circuitos en Paralelo
Los extremos de los elementos están conectados entre sí
El voltaje es el mismo en todos los componentes
La corriente se divide en ramas

📌 Fórmulas:
Resistores:
1/Req = 1/R1 + 1/R2 + 1/R3 + ...

Capacitores:
Ceq = C1 + C2 + C3 + ...

🧮 Funcionalidades del Programa

El programa desarrollado en Java permite:

🔹 Resistores
Calcular resistencia equivalente
Calcular corriente total
Calcular voltaje o corriente en cada resistor
🔹 Capacitores
Calcular capacitancia equivalente
Calcular carga en cada capacitor
Calcular voltaje en cada capacitor
🔹 Extras
Manejo de prefijos:
pico (p)
nano (n)
micro (u)
kilo (k)
mega (M)
giga (G)

Ejemplo:
10kΩ = 10000 Ω
5uF = 0.000005 F

💻 Tecnologías Utilizadas
☕ Java (JDK 8 o superior)
📄 Lectura de archivos .txt
🧠 Programación estructurada
🔢 Cálculos físicos (Ley de Ohm y capacitancia)

📂 Entrada de Datos

El programa permite dos formas:

1. Manual (Consola)

El usuario ingresa:

Tipo de circuito
Valores de resistencias o capacitores
Voltaje

2. Archivo de Texto

Ejemplo de archivo:
resistor serie 12V 3
10kΩ
5kΩ
220Ω
