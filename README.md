"# 2016 Viernes noche Design Dream Team" 

<strong>PARA HACER ANDAR EL XML DEL HIBERNATE</strong><br>
Bajarse el mysql-connector-java-6.0.3.jar

1 - <em>https://downloads.mysql.com/archives/c-j/</em><br>
2- Ir a las <strong>propiedades del proyecto </strong> -> <strong>Java Build Path </strong> -><strong> Libraries</strong> -> <strong>Add External JAR</strong><br>
3- Ir a  <strong>Source </strong> ->  <strong>Add Folder </strong> ->  <strong>test/resources/meta-info  </strong><br>
<strong>MANDALE GAS AMIWO</strong>

<strong> PARA VINCULAR EL TP CON MYSQL </strong><br>
1 - ir al persistence.xml del proyecto, descomentar lo siguiente 

 <!-- <property name="hibernate.connection.driver_class" value="com.mysql.jdbc.Driver" /> 
            <property name="hibernate.connection.url" value="jdbc:mysql://localhost:3306/tp_anual" /> 
            <property name="hibernate.connection.username" value="root" /> 
            <property name="hibernate.connection.password" value="juani" />--> 
            
        y comentar 
        <!--  <property name="hibernate.connection.driver_class" value="org.hsqldb.jdbcDriver" /> 
            <property name="hibernate.connection.url" value="jdbc:hsqldb:mem:app-tests" /> 
            <property name="hibernate.connection.username" value="sa" /> 
            <property name="hibernate.connection.password" value="" /> 
            <property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect" />--> <br>
            
  2- crear una instancia local en mysql con la contraseña que se seteó, crear un nuevo schema, hacer click derecho en este y setearlo como predeterminado para poder hacer las queries que querramos.. una vez hecho esto, en lo descomentado del persistence.xml, en localhost:3306/NOMBRE DEL SCHEMA... en value del username el nombre del local host y la contraseña que settearon en el campo de password.. 
  3- terminado esto, corren los test y una vez que se terminan, van al schema, tablas y hacne un refresh. les debe aparecer las tablas que se persistieron.. para consultar, crear un nuevo query y seleccionan lo que se les cante :)
            
            
