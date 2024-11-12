import { useNavigate } from "react-router-dom";
import { getBodegas } from "../Services/services";
import { useEffect, useState } from "react";

export default function BodegasDisponibles() {
  const navigate = useNavigate();
  const [bodegaSeleccionada, setBodegaSeleccionada] = useState<string>("");
  const [bodegas, setBodegas] = useState<{ nombre: string }[]>([]);
  const [loading, setLoading] = useState<boolean>(true); // Para manejar el estado de carga

  useEffect(() => {
    getBodegas()
      .then((data) => {
        setBodegas(data);
        if (data.length > 0) {
          setBodegaSeleccionada(data[0].nombre); // Selección inicial
        }
      })
      .finally(() => setLoading(false)); // Finalizamos el estado de carga
  }, []);

  const handleContinuar = () => {
    if (bodegaSeleccionada) {
      navigate(`/actualizarBodegas?bodega=${bodegaSeleccionada}`);
    }
  };

  const handleVolver = () => {
    navigate("/inicio");
  };

  return (
    <div className="w-full">
      <header>
        <div className="background-container">
          <h1>BONVINO</h1>
          <h4>Encuentra el vino correcto</h4>
        </div>
      </header>

      <main className="py-4 px-4">
        <h3>Selecciona la bodega a actualizar:</h3>
        {loading ? (
          <p>Cargando bodegas...</p> // Muestra un mensaje mientras se cargan los datos
        ) : bodegas.length === 0 ? (
          <p>No hay bodegas disponibles para actualizar.</p> // Mensaje cuando no hay bodegas
        ) : (
          bodegas.map((bodega, index) => (
            <div className="form-check" key={index}>
              <input
                className="form-check-input"
                type="radio"
                name="bodegaSeleccionada"
                id={`bodega-${index}`}
                value={bodega.nombre}
                checked={bodegaSeleccionada === bodega.nombre}
                onChange={() => setBodegaSeleccionada(bodega.nombre)}
                aria-label={`Seleccionar bodega ${bodega.nombre}`}
              />
              <label className="form-check-label" htmlFor={`bodega-${index}`}>
                {bodega.nombre}
              </label>
            </div>
          ))
        )}

        <div className="px-4 py-4 d-flex justify-content-between">
          <button className="btn btn-dark" onClick={handleVolver}>
            Volver
          </button>
          <button
            className="btn btn-primary"
            onClick={handleContinuar}
            disabled={!bodegaSeleccionada || bodegas.length === 0}
          >
            Confirmar
          </button>
        </div>
      </main>

      <footer className="flex w-full bg-danger-subtle py-4 px-4">
        <div className="container text-center">
          <nav className="d-flex justify-content-evenly">
            <a
              href="https://facebook.com"
              target="_blank"
              rel="noopener noreferrer"
            >
              <img
                id="facebook"
                className="icono"
                src="./src/fotos/face.png"
                alt="Facebook"
              />
            </a>
            <a href="https://x.com" target="_blank" rel="noopener noreferrer">
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
              <img
                id="whats"
                className="icono"
                src="./src/fotos/wsp.webp"
                alt="WhatsApp"
              />
            </a>
          </nav>
        </div>
        <hr />
        <div>
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
