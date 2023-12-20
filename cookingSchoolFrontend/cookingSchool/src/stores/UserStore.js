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
                this.courses = userResponse.data;
                console.log('users geladen', userResponse.data);
            } catch (error) {
                console.error('Error loading users:', error);
            }
        },
        async createUser(data) {
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

                console.log(data);

                const userResponse = await axios.post('http://localhost:8082/admin/courses', userData);
                console.log(userResponse.data);

                this.courses.push(userResponse.data);
            } catch (error) {
                console.error('Error creating user:', error);
            }
        }
    }
});
