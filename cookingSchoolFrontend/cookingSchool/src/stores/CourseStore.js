import {defineStore} from "pinia";
import axios from "axios";
import {ApiUrl} from "@/helper/ApiHelper";

export const useCourseStore = defineStore('course', {
    state: () => ({
        courses: [],
        userCourses: [],
        userRecipes: [],
    }),
    actions: {
        async showCourses() {
            try {
                const courseResponse = await axios.get('http://localhost:8082/courses');
                this.courses = courseResponse.data;
               
            } catch (error) {
                console.error('Error loading courses:', error);
            }
        },

        async createCourse(data) {
           
            try {
                const courseData = {
                    courseTitle: data.courseTitle,
                    description: data.description,
                    teacher: data.teacher,
                    image: data.image,
                    startDate: data.startDate,
                    maxAttendants: data.maxAttendants,
                    price: data.price
                };
               
                const courseResponse = await axios.post(ApiUrl('/admin/courses'), courseData, {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                });
                const createdCourse = courseResponse.data;
                this.courses.push(courseResponse.data);
              
              //bringt nichts  await this.showCourses()
            } catch (error) {
                console.error('Error creating course:', error);
            }
        },

        async bookCourse(userId, courseId){
            try {
                
                await axios.post('http://localhost:8082/users/'+userId+'/book-course/'+courseId, {},{
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                })
                await axios.post('http://localhost:8082/send-email/'+userId);
                
                await this.showCourses()
            } catch (error) {
                console.error('Error booking course:', error);
            }
        },

    async showUserCourses(userId){
       
        const userCoursesResponse = await axios.get('http://localhost:8082/users/'+userId, {
            headers: {
                'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
            }
        });
        

            const userData = {
                userId: userCoursesResponse.data.userId,
                firstname: userCoursesResponse.data.firstname,
                lastname: userCoursesResponse.data.lastname,
                courses: userCoursesResponse.data.courses,
                recipes: userCoursesResponse.data.recipes
            };

            this.userCourses = userData.courses;
        

    },

        //in AdminCourseUserView --- ADMIN
        async getCourseById(courseId) {
            const courseByIdResponse = await axios.get(ApiUrl(`/admin/courses/${courseId}`), {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            });
        
            return courseByIdResponse.data
        },

        //in AdminCourseView --- ADMIN
        async updateCourse(courseId, updatedCourse) {
            try {
                const courseResponse = await axios.put(ApiUrl(`/admin/courses/${courseId}`), updatedCourse, {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                });
                const index = this.courses.findIndex(course => course.courseId === courseId);
                if (index !== -1) {
                    this.courses[index] = courseResponse.data;
                  
                    // bringt nichts   await this.showCourses()
                }
            } catch (error) {
                console.error('Error updating course:', error);
            }
        },

        //in AdminCourseView --- ADMIN
        async deleteCourse(courseId) {
            const deleteResponse = await axios.delete(ApiUrl(`/admin/courses/${courseId}`), {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            });
           
            await this.showCourses();
        }
}   
});
