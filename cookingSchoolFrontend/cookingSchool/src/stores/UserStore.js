import {defineStore} from "pinia";
import axios from "axios";
import {createApiUrl} from "@/helper/ApiHelper";
import {useAuthStore} from "@/stores/AuthStore.js";

export const useUserStore = defineStore('user', {
    state: () => ({
        users: []
    }),
    actions: {
        async loadAllUsers() {
                const userResponse = await axios.get(createApiUrl('/admin/users'))
                this.users = userResponse.data
        },
        async creatUser(newUserDto) {
            const response = await axios.post(createApiUrl('/admin/users'), newUserDto)
            this.users.push(response.data)
            return response.data
        },
        async findUser(userId) {
            const authStore = useAuthStore()
            return authStore.getUser(userId)
        },
        async updateUser(userId, updatedUserDto) {
            if('_id' in updatedUserDto) {
                delete updatedUserDto._id
            }
            await axios.put(createApiUrl(`/users/${userId}`), updatedUserDto)
            const user = this.users.find(user=> user._id === userId)
            if(user >= 0) {
                this.users.splice(user, 1, {
                    _id: userId,
                    ...updatedUserDto
                })
            }
        },
        async deleteUser(userId) {
            await axios.delete(createApiUrl(`/admin/users/${userId}`))
        }
    }
});
