export default function ErrorPage() {
  return (
    <div className="">
      <h1 className="d-flex justify-content-center text-danger">Error 404</h1>
      <h2 className="d-flex justify-content-center">
        La página que buscas no existe
      </h2>
      <button
        className="d-flex justify-content-center btn btn-danger"
        onClick={() => window.history.back()}
      >
        Volver
      </button>
    </div>
  );
}
