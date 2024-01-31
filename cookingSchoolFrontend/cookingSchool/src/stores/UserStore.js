import {defineStore} from "pinia";
import axios from "axios";
import {ApiUrl} from "@/helper/ApiHelper";

export const useUserStore = defineStore('user', {
    state: () => ({
        newUser: '',
        users: []
    }),
    actions: {

        //in AdminUserView --- ADMIN
        async creatUser(newUser) {
            console.log(newUser)
            const config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                }
            }
            const userResponse = await axios.post(ApiUrl('/admin/users'), newUser, config)
            this.users.push(userResponse.data)
        },

        //in AdminUserView, AdminCourseUserView --- ADMIN
        //TODO: Kathy - Stella fragen, ob das so richtig ist mit config mitgeben
        async addUserToCourse(userId, courseId) {
            console.log('store userId', userId)
            const config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                }
            }
            const addUserToCourseResponse = await axios.post(
                ApiUrl(`/admin/${userId}/book-course/${courseId}`),
                {config},
                {timeout: 5000} // Timeout in Millisekunden (hier 5 Sekunden)
            );
            console.log('store addUserToCourse', addUserToCourseResponse);
        },

        //in AdminHomeView, AdminUserView, AdminCourseUserView --- ADMIN
        async showUsers() {
            const config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                }
            }
            const userResponse = await axios.get(ApiUrl('/admin/users'), config);
            console.log(userResponse.data);
            //this.users = userResponse.data;
            this.users = userResponse.data
            //const userId =userResponse.data.userId //hinzugefügt weil id undefined für delete und put
            console.log('users geladen', userResponse.data);
            const userIds = userResponse.data.map(user => user.userId);
            console.log('fuuuuuu...users ids ', userIds)
            return userResponse.data;
        },

        // in ProfileView, AdminHomeView --- APPUSER, ADMIN
        async getUserData(userId) {
            const config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                }
            }
            const response = await axios.get(ApiUrl(`/users/${userId}`), config);
            return response.data;
        },

        // in ProfileView, AdminHomeView --- APPUSER, ADMIN
        async updateUser(userId, updatedUserDto) {
            if ('userId' in updatedUserDto) {
                delete updatedUserDto.userId;
            }
            const config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                }
            }
            await axios.put(ApiUrl(`/users/${userId}`), updatedUserDto, config);
            const user = this.users.find(user => user.userId === userId);
            if (user >= 0) {
                this.users.splice(user, 1, {
                    userId: userId,
                    ...updatedUserDto
                })
            }
        },

        //in AdminUserView, ProfileView(user) --- ADMIN, APPUSER
        async deleteUser(userId) {
            console.log('userId in deleteUser der UserStore:', userId)
            const config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                }
            }
            await axios.delete(ApiUrl(`/users/${userId}`), config);
            await this.showUsers()
        }
    }
});
