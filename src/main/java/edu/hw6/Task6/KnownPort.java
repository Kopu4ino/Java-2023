package edu.hw6.Task6;

public enum KnownPort {
    EPMAP(135, "EPMAP (TCP)", PortType.TCP),
    NETBIOS_NAME_SERVICE(137, "Служба имен NetBIOS (UDP)", PortType.UDP),
    NETBIOS_DATAGRAM_SERVICE(138, "Служба датаграмм NetBIOS (UDP)", PortType.UDP),
    NETBIOS_SESSION_SERVICE(139, "Служба сеансов NetBIOS (TCP)", PortType.TCP),
    MICROSOFT_DS(445, "Microsoft-DS Active Directory (TCP)", PortType.TCP),
    ADOBE_FLASH(843, "Adobe Flash (TCP)", PortType.TCP),
    MYSQL_DATABASE(3306, "MySQL Database", PortType.BOTH),
    POSTGRESQL_DATABASE(5432, "PostgreSQL Database", PortType.BOTH),
    RDP(3389, "Remote Desktop Protocol (RDP)", PortType.TCP),
    MONGODB(27017, "MongoDB Database", PortType.BOTH),
    ORACLE_DATABASE(1521, "Oracle Database", PortType.BOTH);

    private final int port;
    private final String description;
    private final PortType type;

    KnownPort(int port, String description, PortType type) {
        this.port = port;
        this.description = description;
        this.type = type;
    }

    public int getPort() {
        return port;
    }

    public String getDescription() {
        return description;
    }

    public PortType getType() {
        return type;
    }

    public static String getDescriptionByPort(int port, PortType type) {
        for (KnownPort knownPort : values()) {
            if (knownPort.getPort() == port && (knownPort.getType() == type || knownPort.getType() == PortType.BOTH)) {
                return knownPort.getDescription();
            }
        }
        return "";
    }

    public enum PortType {
        TCP,
        UDP,
        BOTH
    }
}

