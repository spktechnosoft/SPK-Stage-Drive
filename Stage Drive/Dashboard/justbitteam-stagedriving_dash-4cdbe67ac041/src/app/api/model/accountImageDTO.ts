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


/**
 * Account images model
 */
export interface AccountImageDTO {
    id?: string;
    created?: string;
    modified?: string;
    smallUri?: string;
    normalUri: string;
    uri?: string;
    largeUri?: string;
    account?: AccountDTO;
}
