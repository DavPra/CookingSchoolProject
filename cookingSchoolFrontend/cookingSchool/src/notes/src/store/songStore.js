import { defineStore } from 'pinia'
import axios from 'axios'
import { createApiUrl } from '@/helper/ApiHelper'

export const useSongStore = defineStore('songs', {
  state: () => ({
    songs: []
  }),
  actions: {
    async loadAllSongs() {
      const response = await axios.get(createApiUrl('/songs'))
      this.songs = response.data
    },
    async createSong(newSongDto) {
      const response = await axios.post(createApiUrl('/songs'), newSongDto)
      this.songs.push(response.data)
      return response.data
    },
    async ensureSong(songId) {
      const song = this.songs.find(song => song._id === songId)
      if(!song) {
        const response = await axios.get(createApiUrl(`/songs/${songId}`))
        this.songs.push(response.data)
      }
    },
    async updateSong(songId, updatedSongDto) {
      if('_id' in updatedSongDto) {
        delete updatedSongDto._id
      }
      await axios.put(createApiUrl(`/songs/${songId}`), updatedSongDto)
      const songIndex = this.songs.findIndex(song => song._id === songId)
      if(songIndex >= 0) {
        this.songs.splice(songIndex, 1, {
          _id: songId,
          ...updatedSongDto
        })
      }
    },
    async deleteSong(songId) {
      await axios.delete(createApiUrl(`/songs/${songId}`))
      const songIndex = this.songs.findIndex(song => song._id === songId)
      if(songIndex >= 0) {
        this.songs.splice(songIndex, 1)
      }
    }
  }
})
