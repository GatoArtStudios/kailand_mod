# Mod de Kailand

## Solucionar los eventos del porcedure de la varita del dictado

Eventos necesarios para el porcedure, la clase padre de los eventos es **Event**.

- **PlayerInteractEvent.LeftClickEmpty**: Evento ,clikc Izquierdo vacio.
    - Method: `onLeftClickEmpty`
- **PlayerEvent.PlayerContainerEvent.Open**: Evento, abrir el inventario.
    - Method: `onContainerOpen`
- **ServerChatEvent**: Evento, Mensaje en chat del server. `Global`
    - Method: `onServerChat`
- **LivingEvent.LivingJumpEvent**: Evento, Entidad salta. `Global`
    - Method: `onLivingJump`
- **TickEvent.PlayerTickEvent**: Evento agachado, moverse
    - Method: `onPlayerTick`
- **ItemEvent.ItemTossEvent**: Evento, Lanzar item
    - Method: `onItemToss`