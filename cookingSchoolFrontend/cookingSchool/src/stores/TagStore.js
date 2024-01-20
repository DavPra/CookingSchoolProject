import { defineStore } from "pinia";
import axios from "axios";

export const useTagStore = defineStore('tag', {
    state: () => ({
        availableTags: []
    }),
    actions: {
        async fetchAvailableTags() {
            try {

                const response = await axios.get('http://localhost:8082/admin/courseTag');
                this.availableTags = response.data.map(tag => ({
                    'item-title': tag.courseTagTitle,
                    'item-value': tag.courseTagId
                }));
                console.log('tagStore', response.data)
            } catch (error) {
                console.error('Error fetching tags:', error)
            }
        },
    }
});
