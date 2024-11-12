import useSearchParams from "../Hooks/Hooks";
import { getBodegasActualizadas } from "../Services/services";
import { useEffect, useState } from "react";

interface Reseña {
  puntaje: number;
}

export default function ActualizarBodegas() {
  const [bodegasActualizada, setBodegasActualizada] = useState<[]>([]);

  const { bodega } = useSearchParams();
  console.log(bodega);

  useEffect(() => {
    getBodegasActualizadas(bodega).then((data) => {
      setBodegasActualizada(data);
      console.log(data);
    });
  }, );

  return (
    <div>
      <header>
        <div className="background-container">
          <h1>BONVINO</h1>
          <h4>Encuentra el vino correcto</h4>
        </div>
      </header>
      <body>
        <main>
          <table className="content-table w-full">
            <thead>
              <tr>
                <th scope="col ">Bodega</th>
                <th scope="col">Nombre del Vino</th>
                <th scope="col">Añada</th>
                <th scope="col">Fecha De Actualizacion</th>
                <th scope="col">Imagen Etiqueta</th>
                <th scope="col">Nota De Cata Bodega</th>
                <th scope="col">Precio ARS</th>
                <th scope="col">Puntaje Promedio</th>
                <th scope="col">Varietales</th>
                <th scope="col">Maridaje</th>
                <th scope="col">Estado</th>
              </tr>
            </thead>
            <tbody id="tablaVinosResumen">
              {bodegasActualizada.map((bodega) => {
                const {
                  vinoAMostrar: {
                    bodega: bodegaInfo,
                    nombre,
                    añada,
                    fecha_ACTUALIZACION,
                    imagen_ETIQUETA,
                    nota_CATA,
                    precioars,
                    reseñas,
                    varietalesAMostrar,
                    maridajesVino,
                  },
                  estado,
                } = bodega;
                // Calcular puntaje promedio
                const puntajePromedio: string =
                  reseñas.length > 0
                  ? (
                    reseñas.reduce(
                      (acc: number, reseña: Reseña) => acc + reseña.puntaje,
                      0
                    ) / reseñas.length
                    ).toFixed(2)
                  : "Sin reseñas";

                const varietales = varietalesAMostrar.join(", ");
                return (
                  <tr key={1}>
                    <th scope="row">{bodegaInfo.nombre}</th>
                    <td>{nombre}</td>
                    <td>{añada}</td>
                    <td>
                      {new Date(fecha_ACTUALIZACION).toLocaleDateString()}
                    </td>
                    <td>
                      <img
                        src={imagen_ETIQUETA}
                        alt={`Etiqueta de ${nombre}`}
                        className="w-20 h-auto"
                      />
                    </td>
                    <td>{nota_CATA}</td>
                    <td>${precioars}</td>
                    <td>{puntajePromedio}</td>
                    <td>{varietales}</td>
                    <td>{maridajesVino}</td>
                    <td>{estado}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </main>
      </body>
      <footer className=" flex w-full bg-danger-subtle p-10">
        <div className="container text-center ">
          <div className="container text-center ">
            <nav className="d-flex justify-content-evenly">
              <a
                href="https://facebook.com"
                target="_blank"
                rel="noopener noreferrer"
              >
                <i className="bi bi-facebook icono"></i>
                <img
                  id="facebook"
                  className="icono"
                  src="./src/fotos/face.png"
                  alt="Facebook"
                />
              </a>

              <a href="https://x.com" target="_blank" rel="noopener noreferrer">
                <i className="bi bi-twitter-x icono"></i>
                <img
                  id="x"
                  className="icono"
                  src="./src/fotos/xgris.png"
                  alt="X (Twitter)"
                />
              </a>

              <a
                href="https://youtube.com"
                target="_blank"
                rel="noopener noreferrer"
              >
                <i className="bi bi-youtube icono"></i>
                <img
                  id="youtub"
                  className="icono"
                  src="./src/fotos/yutubee.png"
                  alt="YouTube"
                />
              </a>

              <a
                href="https://instagram.com"
                target="_blank"
                rel="noopener noreferrer"
              >
                <i className="bi bi-instagram icono"></i>
                <img
                  id="insta"
                  className="icono"
                  src="./src/fotos/instagris.webp"
                  alt="Instagram"
                />
              </a>

              <a
                href="https://whatsapp.com"
                target="_blank"
                rel="noopener noreferrer"
              >
                <i className="bi bi-whatsapp icono"></i>
                <img
                  id="whats"
                  className="icono"
                  src="./src/fotos/wsp.webp"
                  alt="WhatsApp"
                />
              </a>
            </nav>
          </div>
        </div>
        <hr />
        <div className="">
          <h4>Información de contacto</h4>
          <div className="row text-center">
            <div className="col-md-4">Entre Ríos, ER 3100, AR</div>
            <div className="col-md-4">bonvino@gmail.com</div>
            <div className="col-md-4">+54 9 343 1234567</div>
          </div>
        </div>
      </footer>
    </div>
  );
}
