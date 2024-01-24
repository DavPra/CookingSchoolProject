import {defineStore} from "pinia";
import axios  from "axios";

export const useUserStore = defineStore('user', {
    state: () => ({
        newUser:'',
        users: [],
        user: null
    }),
    actions: {
        async showUsers() {
            try {
                const userResponse = await axios.get('http://localhost:8082/admin/users');
                console.log(userResponse.data);
                //this.users = userResponse.data;
                this.users = userResponse.data
                //const userId =userResponse.data.userId //hinzugefügt weil id undefined für delete und put
                console.log('users geladen', userResponse.data);
                const userIds = userResponse.data.map(user => user.userId);
                console.log('fuuuuuu...users ids ', userIds)
                return userResponse.data;
            } catch (error) {
                console.error('Error loading users:', error);
            }
        },  async creatUser(newUser) {
            console.log(newUser)

            const userResponse = await axios.post('http://localhost:8082/admin/users', newUser)
            this.users.push(userResponse.data)
        },


        /*async createUser(data) {
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
            // editingUser, .value, .userId
        }, async updateUser(userId, updatedUser) {
            console.log('goshhhh', userId)
            try {
                const updateUserResponse = await axios.put(`http://localhost:8082/admin/users/${userId}`, updatedUser);
                console.log('User updated successfully:', updateUserResponse.data);
            } catch (error) {
                console.error('Error updating user:', error)
            }

        },*/ async deleteUser(userId) {
            try {
                console.log('userId in deleteUser der UserStore:', userId)
                const deleteUserResponse = await axios.delete(`http://localhost:8082/admin/users/${userId}`);
                await this.showUsers()
            } catch (error) {
                console.error('Fehler beim Löschen des Benutzers:', error)
            }
        },/*async deleteUser(userId){
            console.log(userId)
            const deleteUserResponse = await axios.delete('http://localhost:8082/admin/users/'+userId)
            this.showUsers()
        }'*/
        async getUser(userId) {
            try {
                // Hier sollten Sie den API-Aufruf zum Backend machen, um die Benutzerdaten abzurufen
                const response = await fetch(`/api/users/${userId}`);
                const data = await response.json();

                if (response.ok) {
                    this.user = data;
                } else {
                    console.error('Fehler beim Abrufen der Benutzerdaten');
                }
            } catch (error) {
                console.error('Fehler beim Netzwerkaufruf', error);
            }
        },
        async updateUser(updatedUserData) {
            try {
                // Hier sollten Sie den API-Aufruf zum Backend machen, um die Benutzerdaten zu aktualisieren
                const response = await fetch(`/api/users/${updatedUserData.id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(updatedUserData),
                });

                if (response.ok) {
                    // Aktualisieren Sie den lokalen Store mit den aktualisierten Daten
                    this.user = updatedUserData;
                } else {
                    console.error('Fehler beim Aktualisieren der Benutzerdaten');
                }
            } catch (error) {
                console.error('Fehler beim Netzwerkaufruf', error);
            }
        }
    }
});
