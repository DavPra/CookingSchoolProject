import {defineStore} from "pinia";
import axios  from "axios";
import {useAuthStore} from "@/stores/AuthStore";

const token = window.localStorage.getItem('accessToken');
const config = {
    headers: {
        'Authorization': `Bearer ${token}`
    }
};
export const useUserStore = defineStore('user', {
    state: () => ({
        newUser:'',
        users: []
    }),
    actions: {
            async showUsers() {
                try {
                    const userResponse = await axios.get('http://localhost:8082/admin/users', config);

                    console.log('users geladen', userResponse.data);
                    this.users = userResponse.data;
                    const userIds = userResponse.data.map(user => user.userId);
                    console.log('fuuuuuu...users ids ', userIds);

                    return userResponse.data;
                } catch (error) {
                    console.error('Error loading users:', error);
                }
            },
        },
    async creatUser(newUser) {
        try {
            const userResponse = await axios.post('http://localhost:8082/admin/users', newUser, config) 
            this.users.push(userResponse.data);
        } catch (error) {
            console.error('Fehler beim Erstellen des Benutzers:', error);
        }
    },

    async updateUser(userId, user) {
        try {
            const updateUser = await axios.put(`http://localhost:8082/admin/users/${userId}`, user, config)

            } catch (error) {
            console.error('Fehler beim Aktualisieren des Benutzers:', error);
        }
    },

    async deleteUser(userId) {
        try {

            await axios.delete(`http://localhost:8082/admin/users/${userId}`, config)

            await this.showUsers();
        } catch (error) {
            console.error('Fehler beim Löschen des Benutzers:', error);
        }
    },

});
