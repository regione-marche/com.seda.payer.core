package com.seda.commons.logger;

public interface LoggerInterface {

    public static enum LOGGER_PROPERTY
    {
        ApiElaborationTime("ApiElaborationTime"),
        ApiMethod("ApiMethod"),
        ApiRequestBody("ApiRequestBody"),
        ApiResponseBody("ApiResponseBody"),
        ApiResponseStatus("ApiResponseStatus"),
        ApiUri("ApiUri"),
        ApplicationContext("ApplicationContext"),
        Category("Category"),
        CompanyId("CompanyId"),
        IUV("IUV"),
        JobName("JobName"),
        JVMId("JVMId"),
        LoggedMethodInfo("LoggedMethodInfo"),
        LoggingNodeName("LoggingNodeName"),
        LoggingSourceType("LoggingSourceType"),
        Message("Message"),
        MessageContentType("MessageContentType"),
        POD("POD"),
        PortalInstanceId("PortalInstanceId"),
        ServerName("ServerName"),
        SessionCreationTime("SessionCreationTime"),
        SessionId("SessionId"),
        Severity("Severity"),
        SiteCodiceAmministrazione("SiteCodiceAmministrazione"),
        SoapAction("SoapAction"),
        SoapDirection("SoapDirection"),
        SoapUri("SoapUri"),
        ThreadId("ThreadId"),
        ThreadName("ThreadName"),
        Throwable("Throwable"),
        Timestamp("Timestamp"),
        UserCodiceFiscale("UserCodiceFiscale"),
        UserEmailAddress("UserEmailAddress"),
        UserId("UserId"),
        UserLegalEmailAddress("UserLegalEmailAddress"),
        UserLoginIP("UserLoginIP"),
        UserLoginTimestamp("UserLoginTimestamp"),
        UserName("UserName"),
        UserPrincipalName("UserPrincipalName"),
        Direction("Direction")
        ;


        private final String name;

        private LOGGER_PROPERTY(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public static LOGGER_PROPERTY byName(String name)
        {
            for (LOGGER_PROPERTY value : values())
            {
                if (value.name.equals(name))
                    return value;
            }

            return null;
        }

    };

}