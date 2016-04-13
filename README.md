"# 2016 Viernes noche Design Dream Team" 
Direccion faltan atributos :  el código postal, localidad, barrio, provincia, país,
Requerimientos funcionales: Buscar puntos de interés, según criterios de búsqueda establecidos por el usuario
                            Obtener información de un cierto punto
                            Visualizar el resultado de las consultas
                            Almacenar información de la consulta, con fines estadísticos. 

0A => Definido requerimientos y un pequeño diagrama UML (HECHO)

0B => POI =>Si se encuentra a menos de X metros de otro POI ( Falta generar un radio de distancia,HECHO distancia hacia otro POI)
            Si es válido: no puede haber POIs sin geolocalizar ni a los que les falte nombre. (HECHO con el constructor)
            
1  => Cálculo de cercanía => Un POI esta cerca de otro si esta a menos de 5 cuadras (HECHO)
                             Un parada de  colectivo a menos de una cuadra.(HECHO)
                             Los CGP, si su coordenada está dentro de la zona delimitada por la  comuna.(HECHO)
                             Locales comerciales, cada rubro define el radio de cercanía. Ejemplo: para las librerías                                         escolares se considera cerca si está en un radio de 5 cuadras, para un kiosco de diarios en                                       cambio son 2 cuadras, y así sucesivamente.(HECHO)

   Cálculo de disponibilidad => Colectivos está disponible a toda hora (HECHO)
                                CGP: si se ingresa un valor x (el nombre de un servicio), la fecha debe estar en el rango de atención. Ej: si es sábado no se atiende Rentas, no está disponible. Si es lunes a las 20hs tampoco se encuentra disponible este servicio. En cambio si es lunes a las 10:30hs sí se encuentra disponible.
si no se ingresa un valor x, considerar que exista al menos un servicio en el CGP que esté atendiendo en ese momento.(HECHO)
                                Bancos, el mismo comportamiento que para los CGP pero considerando el horario de atención bancario (Lunes a Viernes de 10hs a 15hs)(HECHO)
                                locales comerciales,  considerar el horario de atención del local. Ejemplo: para Carrousel el horario de atención es de Lunes a Sábado de 10hs a 13hs y de 17hs a 20:30hs. Si son las 15 horas de un martes no debe estar disponible.(HECHO)

  Búsqueda de puntos => Si se ingresa una palabra clave, se debería encontrar todos los puntos que estén etiquetados mediante                          esa palabra clave.  (creo que HECHO)
  
  REQUERIMIENTOS FUNCIONALES => Determinar cuándo un punto de interés está cerca de una coordenada geográfica. 
                                Dado un momento (fecha:hora:minutos:segundos) y un valor x, determinar si un negocio está                                        disponible.
                                Buscar un punto de interés en base a un texto libre 
                                Diseñar e implementar los casos de prueba para cada uno de los 3 requerimientos anteriores.

            
      
