package com.fmu.rpgcharactercreation.converters

import com.fmu.rpgcharactercreation.enums.Origin


fun toOrigin(originString: String?): Origin? {
    return when (originString) {
        "Acadêmico" -> Origin.ACADEMICIAN
        "Agente de Saude" -> Origin.SALUTARY_AGENT
        "Artista" -> Origin.ARTIST
        "Atleta" -> Origin.ATHLETE
        "Cientista Forense" -> Origin.SCIENTIST_FORENSIC
        "Criminoso" -> Origin.CRIMINAL
        "Escritor" -> Origin.WRITER
        "Jornalista" -> Origin.JOURNALIST
        "Lutador" -> Origin.FIGHTER
        "Militar" -> Origin.MILITARY
        "T.I" -> Origin.IT
        "Religioso" -> Origin.RELIGIOUS
        else -> null  // ou um valor default
    }
}
