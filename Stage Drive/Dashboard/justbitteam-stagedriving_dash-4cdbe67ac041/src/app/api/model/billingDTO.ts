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
 * Account billing methods model
 */
export interface BillingDTO {
    id?: string;
    created?: string;
    modified?: string;
    provider: string;
    iban: string;
    coordinate?: string;
    status?: string;
    swift?: string;
    note?: string;
    account?: AccountDTO;
}