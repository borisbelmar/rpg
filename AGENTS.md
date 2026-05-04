# AGENTS.md

## Reglas del proyecto

- **NUNCA pushear a GitHub sin preguntar primero al usuario.** Si una tarea implica `git push`, `git commit` o crear repositorios remotos, consulta antes con el usuario.
- Nunca crear documentación no solicitada.
- Mantener respuestas concisas.

## Contexto del proyecto

### Propósito
RPG por consola en Java que ilustra los 4 pilares de la POO: Encapsulamiento, Herencia, Polimorfismo y Abstracción.

### Tecnología
- Java (proyecto IntelliJ IDEA)
- Sin dependencias externas

### Estructura
```
src/
├── Main.java              ← Punto de entrada
└── game/
    ├── Character.java     ← Clase base abstracta (name, hp)
    ├── Combatant.java     ← Puente abstracto (extiende Character, implementa Combat)
    ├── Combat.java        ← Interfaz (método attack)
    ├── Hero.java          ← Personaje jugable (equipa armas)
    ├── Monster.java       ← Enemigo (daño fijo)
    ├── Item.java          ← Objeto base (name, price)
    ├── Weapon.java        ← Arma (extiende Item, tiene damage y type)
    └── Encounter.java     ← Gestiona el combate por turnos
```

### Jerarquía de clases
- `Item` ← `Weapon`
- `Character` (abstract) ← `Combatant` (abstract, implementa `Combat`) ← `Hero` / `Monster`
- `Combat` (interfaz) implementada por `Combatant`

### Convenciones
- Atributos siempre `private` con getters/setters
- Clases en paquete `game`
- Combate por turnos alternos hasta que un combatiente llegue a HP ≤ 0
