import { ETipoAccionCRUD } from './tipo-accion';

export class ParametroDialogo<T, U> {

    accion: ETipoAccionCRUD;
    objeto: T;
    objetoReferencia?: U;
    resultado?: string;
    datoAdicional?: any;
    objetosExtra?: any;
    coTablaInstancia?: any;
    idProceso?: number;
    incluyeDocumentos?: boolean;
    esPropia?: boolean
}
