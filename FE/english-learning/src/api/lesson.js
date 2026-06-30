import api from "./axios";

export function getLessonByCourse(id){

    return api.get(`/lessons/course/${id}`);

}