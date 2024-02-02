import {defineStore} from "pinia";
import axios  from "axios";
import {ApiUrl} from "@/helper/ApiHelper";


export const useUserStore = defineStore('user', {
    state: () => ({
        newUser:'',
        users: []
    }),
    actions: {
        async showUsers() {
            try {
                const userResponse = await axios.get(ApiUrl('/admin/users'), {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                });
                
                
                this.users = userResponse.data
                
                const userIds = userResponse.data.map(user => user.userId);
                
                return userResponse.data;
            } catch (error) {
                
            }
        }, async creatUser(newUser) {
           

            const userResponse = await axios.post(ApiUrl('/admin/users'), newUser, {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            })
            this.users.push(userResponse.data)

        }, async updateUser(userId, user) {

            const updateUser = await axios.put('http://localhost:8082/admin/users/' + userId, user, {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                }
            )


        },

        async deleteUser(userId) {
            try {
               
                const deleteUserResponse = await axios.delete(`http://localhost:8082/admin/users/${userId}`, {
                        headers: {
                            'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                        }
                    });
            } catch (error) {
                console.error('Fehler beim Löschen des Benutzers:', error)
            }
        },

        async addUserToCourse(userId, courseId) {
            
            try {
                const addUserToCourseResponse = await axios.post(
                    `http://localhost:8082/admin/${userId}/book-course/${courseId}`,
                    {},
                    {
                        headers: {
                            'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                        }
                    },
                    {timeout: 5000} // Timeout in Millisekunden (hier 5 Sekunden)
                );
               
            } catch (error) {
                console.error('Error adding user to course:', error);
            }

        }
    }

});
