import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";
import { Route, Switch } from "wouter";
import PaginaInicio from "./Components/PaginaInicio.tsx";
import ActualizarBodegas from "./Components/ActuzalizarBodega.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Switch>
      <Route path="/" component={App} />
      <Route path="/inicio" component={PaginaInicio} />
      <Route path="/bodegas" component={ActualizarBodegas} />
    </Switch>
  </StrictMode>
);
