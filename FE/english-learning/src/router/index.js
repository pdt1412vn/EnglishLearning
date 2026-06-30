import {createRouter,createWebHistory} from "vue-router";

import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Course from "../views/Course.vue";
import Lesson from "../views/Lesson.vue";
import Vocabulary from "../views/Vocabulary.vue";
import Quiz from "../views/Quiz.vue";
import Result from "../views/Result.vue";

const routes=[

    {

        path:"/",

        component:Home

    },

    {

        path:"/login",

        component:Login

    },

    {

        path:"/courses",

        component:Course

    },

    {

        path:"/lesson/:id",

        component:Lesson

    },

    {

        path:"/vocabulary/:id",

        component:Vocabulary

    },

    {

        path:"/quiz/:id",

        component:Quiz

    },

    {

        path:"/result",

        component:Result

    },
    {

    path:"/course/:id",

    component:()=>import("../views/CourseDetail.vue")

    },

]

export default createRouter({

    history:createWebHistory(),

    routes

})