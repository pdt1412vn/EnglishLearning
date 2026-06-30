import api from "./axios";

export function getVocabulary(id){

    return api.get(`/vocabularies/lesson/${id}`);

}