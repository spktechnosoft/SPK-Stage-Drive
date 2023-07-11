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


/**
 * Image model
 */
export interface ImageDTO {
    id?: string;
    created?: string;
    modified?: string;
    uri: string;
    content?: string;
    smallUri?: string;
    normalUri?: string;
    largeUri?: string;
    category?: string;
}
