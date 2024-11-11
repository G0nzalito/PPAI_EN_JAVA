import { useNavigate } from "react-router-dom";

export default function PaginaInicio() {
  const navigate = useNavigate();

  function handleClick() {
    navigate("/bodegasDisponibles");
  }

  return (
    <div className="w-full">
      <header>
        <div className="background-container">
          <h1>BONVINO</h1>
          <h4>Encuentra el vino correcto</h4>
        </div>
      </header>
      <body className="">
        <div className="py-4 d-flex justify-content-center">
          <button className="btn btn-danger" onClick={handleClick}>
            Ver bodegas disponibles
          </button>
        </div>
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
