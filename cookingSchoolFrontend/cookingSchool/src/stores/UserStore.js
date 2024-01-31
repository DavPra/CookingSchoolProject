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
            const userResponse = await axios.post(ApiUrl('/admin/users'), newUser, {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            })
            this.users.push(userResponse.data)
        },

        //in AdminUserView, AdminCourseUserView --- ADMIN
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
            const userResponse = await axios.get(ApiUrl('/admin/users'), {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            });
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
            const response = await axios.get(ApiUrl(`/users/${userId}`), {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
                });
            return response.data;
        },

        async updateUser(userId, user) {
            try {
                const updateUser = await axios.put(`http://localhost:8082/admin/users/${userId}`, user, config)

            } catch (error) {
                console.error('Fehler beim Aktualisieren des Benutzers:', error);
            }
        },

        //in AdminUserView, ProfileView(user) --- ADMIN, APPUSER
        async deleteUser(userId) {
            console.log('userId in deleteUser der UserStore:', userId)
            await axios.delete(ApiUrl(`/users/${userId}`), {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            });
            await this.showUsers()
        }
    }
});
