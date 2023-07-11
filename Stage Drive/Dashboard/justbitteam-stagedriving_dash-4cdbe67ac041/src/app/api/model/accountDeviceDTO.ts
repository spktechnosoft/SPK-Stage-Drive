/**
 * Stage Driving API
 * API specifications
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { AccountDTO } from './accountDTO';


export interface AccountDeviceDTO {
    id?: string;
    created?: string;
    modified?: string;
    account?: AccountDTO;
    os?: string;
    provider?: string;
    token?: string;
}
