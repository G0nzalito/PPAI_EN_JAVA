import axios from "axios";
const URL = "http://localhost:8081/gestor";
const URL2 = "http://localhost:8081/gestor/bodegas"; //para datos de seleccionar la bodega
const URL3 = "http://localhost:8081/gestor/resultadoTablas"; //datos para poner en la tabla

export const getBodegas = async () => {
  try {
    const response = await axios.get(URL2);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const getBodegasActualizadas = async (nombreBodega: string) => {
  try {
    const response = await axios.get(`${URL3}?bodegaSeleccionada=${nombreBodega}`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};
