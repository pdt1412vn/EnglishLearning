import api from "./axios";

export function getCourses(){

    return api.get("/courses");

}

export function getCourse(id){

    return api.get("/courses/"+id);

}