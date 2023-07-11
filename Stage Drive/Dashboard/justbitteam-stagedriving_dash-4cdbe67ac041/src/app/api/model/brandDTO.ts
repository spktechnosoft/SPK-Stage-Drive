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
import { ItemDTO } from './itemDTO';


export interface BrandDTO {
    id?: string;
    created?: string;
    modified?: string;
    status?: string;
    visible?: boolean;
    name?: string;
    description?: string;
    uri?: string;
    base?: boolean;
    items?: Array<ItemDTO>;
}