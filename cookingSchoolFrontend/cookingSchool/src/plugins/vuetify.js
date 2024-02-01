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
                    secondary: '#EFEBE9',
                }
            }
        }
    },
    icons: {
        defaultSet: 'mdi',
    },
}
)