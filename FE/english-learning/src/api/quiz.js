import api from "./axios";

export function getQuiz(id){

    return api.get(`/quizzes/lesson/${id}`);

}