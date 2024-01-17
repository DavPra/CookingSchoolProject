import {defineStore} from "pinia";
import axios from "axios";


export const useUserStore = defineStore('user', {
    state: () => ({
        users: []
    }),
    actions: {
        async showUsers() {
            try {
                const userResponse = await axios.get('http://localhost:8082/admin/users');
                console.log(userResponse.data);
                this.users = userResponse.data;
                console.log('users geladen', userResponse.data);
                return userResponse.data;
            } catch (error) {
                console.error('Error loading users:', error);
            }
        },
        async createUser(data) {
            console.log("!!!", data, data.admin);
            try {
                const userData = {
                    firstname: data.firstname,
                    lastname: data.lastname,
                    address: data.address,
                    mobile: data.mobile,
                    email: data.email,
                    password: data.password,
                    username: data.username,
                    isAdmin: data.isAdmin
                };
                console.log(userData);
                  const userResponse = await axios.post('http://localhost:8082/admin/users', userData);
                console.log(userResponse.data);
               //this.users.push(userResponse.data);
               return userResponse.data;
            } catch (error) {
                console.error('Error creating user:', error);
            }
        }, async updateUser(userId, updatedUser) {
            console.log('goshhhh')
            try {
                const updateUserResponse = await axios.put(`http://localhost:8082/admin/users/${userId}`, updatedUser);
                console.log('User updated successfully:', updateUserResponse.data);
            } catch (error) {
                console.error('Error updating user:', error);
            }

        }
    }
});
