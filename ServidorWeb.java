import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ServidorWeb
{
    public static void main(String argv[]) throws Exception
    {
        /// Número de puerto
        int puerto = 6789;

        // Número de hilos
        int numHilos = 20;
            
        // Crea el socket de escucha.
        ServerSocket socketBienvenida = new ServerSocket(puerto);

        System.out.println("Servidor iniciado en el puerto "+ puerto);

        /// Procesando solicitud de http en un ciclo finito
        while (true) {

            // Escuhando las solicitudes de conexión TCP.
            Socket socketConexion = socketBienvenida.accept();

            // Contruccion de objeto para procesar el mensaje de solicitud http
            SolicitudHttp solicitud = new SolicitudHttp(socketConexion);

            // Creación de hilo para procesar solicitud
            //Thread hilo = new Thread(solicitud);
            
            // Inicia el hilo
            //hilo.start();

            ExecutorService hilos = Executors.newFixedThreadPool(numHilos);

            hilos.submit(solicitud);

        }
    }
}

