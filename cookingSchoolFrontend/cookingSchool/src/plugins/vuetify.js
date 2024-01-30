import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'
import {createVuetify} from 'vuetify'
import * as deep from "vuetify/util/colors";

export const vuetify = createVuetify(
    {
    theme: {
        themes: {
            light: {
                colors: {
                    primary: '#cb5400',
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