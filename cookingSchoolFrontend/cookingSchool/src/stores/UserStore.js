import {defineStore} from "pinia";
import axios  from "axios";
import {createApiUrl} from "@/helper/ApiHelper";

export const useUserStore = defineStore('user', {
    state: () => ({
        newUser:'',
        users: []
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
        },  async creatUser(newUser){
            console.log(newUser)

            const userResponse = await axios.post('http://localhost:8082/admin/users', newUser )
            this.users.push(userResponse.data)

        },

        async getUser(userId) {
            const response = await axios.get(createApiUrl('/admin/users/' + userId))
            this.users = await response.data
            console.log(this.users)
          },
          
          async deleteUser(userId) {
            try {
                console.log('userId in deleteUser der UserStore:', userId)
                await axios.delete(`http://localhost:8082/admin/users/${userId}`);
                await this.showUsers()
            } catch (error) {
                console.error('Fehler beim Löschen des Benutzers:', error)
            }
        },

        async updateUser(userId, updatedUserDto) {
            if('userId' in updatedUserDto) {
                delete updatedUserDto.userId;
            }
            await axios.put(createApiUrl(`/admin/users/${userId}`), updatedUserDto);
            const user = this.users.find(user => user.userId === userId);
            if(user >= 0) {
                this.users.splice(user, 1, {
                    userId: userId,
                    ...updatedUserDto
                })
            }
        }
    },
    }
);
