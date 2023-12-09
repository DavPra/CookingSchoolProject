import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'
import {createVuetify} from 'vuetify'

export const vuetify = createVuetify(
    {
    theme: {
        themes: {
            light: {
                colors: {
                    primary: '#FF5722',
                    secondary: '#9E9E9E',
                }
            }
        }
    },
    icons: {
        defaultSet: 'mdi',
    },
}
)