import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";
import { Route, Switch } from "wouter";
import PaginaInicio from "./Components/PaginaInicio.tsx";
import ActualizarBodegas from "./Components/ActuzalizarBodega.tsx";
import BodegasDisponibles from "./Components/BodegasDisponibles.tsx";
import { BrowserRouter } from "react-router-dom";
import ErrorPage from "./Components/ErrorPage.tsx";

createRoot(document.getElementById("root")!).render(
    <BrowserRouter>
      <Switch>
        <Route path="/" component={App} />
        <Route path="/inicio" component={PaginaInicio} />
        <Route path="/actualizarBodegas" component={ActualizarBodegas} />
        <Route path="/bodegasDisponibles" component={BodegasDisponibles} />
        <Route path="*" component={ErrorPage} />
      </Switch>
    </BrowserRouter>
);
