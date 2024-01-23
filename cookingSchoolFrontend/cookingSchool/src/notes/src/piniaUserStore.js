import { defineStore } from 'pinia'
import axios from "axios";
import {createApiUrl} from "@/helper/ApiHelper";

export const useUserStore = defineStore('user', {
    state: () => ({
        /** @type {{ userId: number, firstname: string, lastname: string, address: string, mobile: string, email: string, username: string, isAdmin: boolean }[]} */
        users: []
    }),
    getters: {
        finishedTodos(state) {
            // autocompletion! ✨
            return state.todos.filter((todo) => todo.isFinished)
        },
        unfinishedTodos(state) {
            return state.todos.filter((todo) => !todo.isFinished)
        },
        /**
         * @returns {{ text: string, id: number, isFinished: boolean }[]}
         */
        filteredTodos(state) {
            if (this.filter === 'finished') {
                // call other getters with autocompletion ✨
                return this.finishedTodos
            } else if (this.filter === 'unfinished') {
                return this.unfinishedTodos
            }
            return this.todos
        },
        getUserById: (state) => (userId) => {
            return state.users.find(user => user.userId === userId)
        }
    },
    actions: {
        // any amount of arguments, return a promise or not
        async addUser(userData) {
            // you can directly mutate the state
            const userResponse = await axios.post(createApiUrl('/admin/users'), userData);
            this.users.push(userResponse.data)
        },
        async getUsers() {
            const userResponse = await axios.get(createApiUrl('/admin/users'));
            this.users = userResponse.data
        },
        async updateUser(userId, updatedUser) {
            const updateUser = await axios.put(createApiUrl('/admin/users/' + userId), updatedUser)
            this.users = this.users.map(user => {
                if (user.userId === userId) {
                    return updateUser.data
                }
                return user
            })
        },
        async deleteUser(userId) {
            const deleteUser = await axios.delete(createApiUrl('/admin/users/' + userId))
            this.users = this.users.filter(user => user.userId !== userId)
        }
    }
})