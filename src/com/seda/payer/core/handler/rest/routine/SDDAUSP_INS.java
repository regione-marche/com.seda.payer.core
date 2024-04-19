package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SDDAUSP_INS extends ARestRoutine {
    @Override
    protected String routine() {
        return "SDDAUSP_INS";
    }

    @Override
    protected Map<Integer, String> inParameterMap() {

        int p = 1;

        Map<Integer, String> inParameterMap = new HashMap<Integer, String>();

        inParameterMap.put(p++, "I_DAU_CUTECUTE");                     //DAU_CUTECUTE
        inParameterMap.put(p++, "I_DAU_CDCSCSIA");                    //DAU_CDCSCSIA
        inParameterMap.put(p++, "I_DAU_CDAUTPAU");           //DAU_CDAUTPAU
        inParameterMap.put(p++, "I_DAU_CDAUCOAU");         //DAU_CDAUCOAU
        inParameterMap.put(p++, "I_DAU_FDAUSTAT");                        //DAU_FDAUSTAT
        inParameterMap.put(p++, "I_DAU_CDAUCFIS");  //DAU_CDAUCFIS
        inParameterMap.put(p++, "I_DAU_CDAUCFII");    //DAU_CDAUCFII
        inParameterMap.put(p++, "I_DAU_CDAUCPCC");       //DAU_CDAUCPCC
        inParameterMap.put(p++, "I_DAU_CDAUCINT");   //DAU_CDAUCINT
        inParameterMap.put(p++, "I_DAU_CDAUCABI");         //DAU_CDAUCABI
        inParameterMap.put(p++, "I_DAU_CDAUCCAB");         //DAU_CDAUCCAB
        inParameterMap.put(p++, "I_DAU_CDAUCNUM");            //DAU_CDAUCNUM
        inParameterMap.put(p++, "I_DAU_CDAUCCIN");         //DAU_CDAUCCIN
        inParameterMap.put(p++, "I_DAU_GDAUPREV");         //DAU_GDAUPREV
        inParameterMap.put(p++, "I_DAU_CDAUCPRO");            //DAU_CDAUCPRO
        inParameterMap.put(p++, "I_DAU_CDAUTIPP");              //DAU_CDAUTIPP
        inParameterMap.put(p++, "I_DAU_CDAUDOCO");           //DAU_CDAUDOCO
        inParameterMap.put(p++, "I_DAU_GDAUDINV");           //DAU_GDAUDINV
        inParameterMap.put(p++, "I_DAU_GDAUDFNV");             //DAU_GDAUDFNV
        inParameterMap.put(p++, "I_DAU_GDAUREVO");                   //DAU_GDAUREVO
        inParameterMap.put(p++, "I_DAU_CDAUTPAG");                //DAU_CDAUTPAG
        inParameterMap.put(p++, "I_DAU_CDVIVOCI");            //DAU_CDVIVOCI
        inParameterMap.put(p++, "I_DAU_CDAUIDEB");                   //DAU_CDAUIDEB
        inParameterMap.put(p++, "I_DAU_CDVCABIA");      //DAU_CDVCABIA
        inParameterMap.put(p++, "I_DAU_FDAUSOSP");              //DAU_FDAUSOSP
        inParameterMap.put(p++, "I_DAU_GDAUSOSP");              //DAU_GDAUSOSP
        inParameterMap.put(p++, "I_DAU_CDAUOPES");         //DAU_CDAUOPES
        inParameterMap.put(p++, "I_DAU_GDAUREVS");        //DAU_GDAUREVS
        inParameterMap.put(p++, "I_DAU_CDAUOPER");   //DAU_CDAUOPER
        inParameterMap.put(p++, "I_DAU_FDAUFRIM");          //DAU_FDAUFRIM
        inParameterMap.put(p++, "I_DAU_IDAUIMPP");                //DAU_IDAUIMPP
        inParameterMap.put(p++, "I_DAU_CDAUCBIC");                    //DAU_CDAUCBIC
        inParameterMap.put(p++, "I_DAU_FDAUCCON");     //DAU_FDAUCCON
        inParameterMap.put(p++, "I_DAU_GDAUALLI");          //DAU_GDAUALLI
        inParameterMap.put(p++, "I_DAU_FDAUFALL");         //DAU_FDAUFALL
        inParameterMap.put(p++, "I_DAU_GDAUESIC");     //DAU_GDAUESIC
        inParameterMap.put(p++, "I_DAU_CDAUTPMA");             //DAU_CDAUTPMA
        inParameterMap.put(p++, "I_DAU_CDAUDTMA");  //DAU_CDAUDTMA
        inParameterMap.put(p++, "I_DAU_CDAUTPIN");             //DAU_CDAUTPIN
        inParameterMap.put(p++, "I_DAU_CDAUOPEI");         //DAU_CDAUOPEI
        inParameterMap.put(p++, "I_DAU_GDAUINSE");              //DAU_GDAUINSE
        inParameterMap.put(p++, "I_DAU_CDAUOPEV");          //DAU_CDAUOPEV
        inParameterMap.put(p++, "I_DAU_GDAUVARI");               //DAU_GDAUVARI
        inParameterMap.put(p++, "I_DAU_GDAUSOTT");           //DAU_GDAUSOTT
        inParameterMap.put(p++, "I_DAU_CDAUMIGR");                 //DAU_CDAUMIGR
        inParameterMap.put(p++, "I_DAU_FDAUFVAR");               //DAU_FDAUFVAR
        inParameterMap.put(p++, "I_DAU_CDAUMAIL");                         //DAU_CDAUMAIL
        inParameterMap.put(p++, "O_HV_SQLCODE");                    //HV_SQLCODE

        /*
        inParameterMap.put(p++, "data_in_cutecute_in");                     //DAU_CUTECUTE
        inParameterMap.put(p++, "data_in_codicesia_in");                    //DAU_CDCSCSIA
        inParameterMap.put(p++, "data_in_tipoautorizzazione_in");           //DAU_CDAUTPAU
        inParameterMap.put(p++, "data_in_codiceAutorizzazione_in");         //DAU_CDAUCOAU
        inParameterMap.put(p++, "data_in_stato_in");                        //DAU_FDAUSTAT
        inParameterMap.put(p++, "data_in_codiceFiscaleSottoscrittore_in");  //DAU_CDAUCFIS
        inParameterMap.put(p++, "data_in_codiceFiscaleIntestatario_in");    //DAU_CDAUCFII
        inParameterMap.put(p++, "data_in_codicePaeseCcAccredito_in");       //DAU_CDAUCPCC
        inParameterMap.put(p++, "data_in_codiceControlloCcAccredito_in");   //DAU_CDAUCINT
        inParameterMap.put(p++, "data_in_codiceAbiCcAccredito_in");         //DAU_CDAUCABI
        inParameterMap.put(p++, "data_in_codiceCabCcAccredito_in");         //DAU_CDAUCCAB
        inParameterMap.put(p++, "data_in_numeroCcAccredito_in");            //DAU_CDAUCNUM
        inParameterMap.put(p++, "data_in_codiceCinCcAccredito_in");         //DAU_CDAUCCIN
        inParameterMap.put(p++, "data_in_dataPrecedenteRevoca_in");         //DAU_GDAUPREV
        inParameterMap.put(p++, "data_in_canaleProvenienza_in");            //DAU_CDAUCPRO
        inParameterMap.put(p++, "data_in_tipoProvenienza_in");              //DAU_CDAUTIPP
        inParameterMap.put(p++, "data_in_idDocumentoOrigine_in");           //DAU_CDAUDOCO
        inParameterMap.put(p++, "data_in_dataInizioValidita_in");           //DAU_GDAUDINV
        inParameterMap.put(p++, "data_in_dataFineValidita_in");             //DAU_GDAUDFNV
        inParameterMap.put(p++, "data_in_dataRevoca_in");                   //DAU_GDAUREVO
        inParameterMap.put(p++, "data_in_tipoPagamento_in");                //DAU_CDAUTPAG
        inParameterMap.put(p++, "data_in_codiceVoceIncasso_in");            //DAU_CDVIVOCI
        inParameterMap.put(p++, "data_in_idDebitore_in");                   //DAU_CDAUIDEB
        inParameterMap.put(p++, "data_in_codiceAbiCCAllineamento_in");      //DAU_CDVCABIA
        inParameterMap.put(p++, "data_in_flagSospensione_in");              //DAU_FDAUSOSP
        inParameterMap.put(p++, "data_in_dataSospensione_in");              //DAU_GDAUSOSP
        inParameterMap.put(p++, "data_in_operatoreSospensione_in");         //DAU_CDAUOPES
        inParameterMap.put(p++, "data_in_dataRevocaSospensione_in");        //DAU_GDAUREVS
        inParameterMap.put(p++, "data_in_operatoreRevocaSospensione_in");   //DAU_CDAUOPER
        inParameterMap.put(p++, "data_in_flagFacoltaRimborso_in");          //DAU_FDAUFRIM
        inParameterMap.put(p++, "data_in_impPrefissato_in");                //DAU_IDAUIMPP
        inParameterMap.put(p++, "data_in_codiceBic_in");                    //DAU_CDAUCBIC
        inParameterMap.put(p++, "data_in_flagClassificazioneConto_in");     //DAU_FDAUCCON
        inParameterMap.put(p++, "data_in_dataRicAllineamento_in");          //DAU_GDAUALLI
        inParameterMap.put(p++, "data_in_esitoRicAllineamento_in");         //DAU_FDAUFALL
        inParameterMap.put(p++, "data_in_dataEsitoRicAllineamento_in");     //DAU_GDAUESIC
        inParameterMap.put(p++, "data_in_tipologiaMandato_in");             //DAU_CDAUTPMA
        inParameterMap.put(p++, "data_in_descrizioneTipologiaMandato_in");  //DAU_CDAUDTMA
        inParameterMap.put(p++, "data_in_tipologiaIncasso_in");             //DAU_CDAUTPIN
        inParameterMap.put(p++, "data_in_operatoreInserimento_in");         //DAU_CDAUOPEI
        inParameterMap.put(p++, "data_in_dataInserimento_in");              //DAU_GDAUINSE
        inParameterMap.put(p++, "data_in_operatoreVariazione_in");          //DAU_CDAUOPEV
        inParameterMap.put(p++, "data_in_dataVariazione_in");               //DAU_GDAUVARI
        inParameterMap.put(p++, "data_in_dataSottoscrizione_in");           //DAU_GDAUSOTT
        inParameterMap.put(p++, "data_in_DAU_CDAUMIGR_in");                 //DAU_CDAUMIGR
        inParameterMap.put(p++, "data_in_flagVariazione_in");               //DAU_FDAUFVAR
        inParameterMap.put(p++, "data_in_mail_in");                         //DAU_CDAUMAIL
        inParameterMap.put(p++, "data_ou_hvsqlcode_in");                    //HV_SQLCODE
*/
        return inParameterMap;
    }

    @Override
    protected Map<Integer, String> outParameterMap() {

        int p = 1;

        Map<Integer, String> outParameterMap = new HashMap<Integer, String>();

        /*
        * outParameterMap.put(p++, "data_ou_hvsqlcode_out");                  //HV_SQLCODE
        * */
        outParameterMap.put(p++, "O_HV_SQLCODE");                  //HV_SQLCODE

        return outParameterMap;
    }

    @Override
    protected Map<Integer, Map<Integer, String>> resultSetsMap() {

        //NB non restituisce nulla

        //return null;

       // Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();

        return null; //resultSetsMap;
    }
}
