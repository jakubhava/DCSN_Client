/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cuni.mff.bc.client;

import cz.cuni.mff.bc.api.enums.ProjectState;
import cz.cuni.mff.bc.api.main.CustomIO;
import java.io.File;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UP711643
 */
public class ClientCommands {

    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    private Client client;

    public ClientCommands(Client client) {
        this.client = client;
    }

    public static String[] parseCommand(String params) {
        return params.split("\\s+");
    }

    public static boolean checkParamNum(int expected, String[] params) {
        if (expected == params.length) {
            return true;
        } else {
            return false;
        }
    }

    public void createProjectFrom(String[] params) {
        if (checkParamNum(3, params)) {
            try {
                Path projectJar = client.getUploadFileLocation(params[0]);
                Path destinationJar = client.getUploadFileLocation(params[1]);
                if (CustomIO.getExtension(destinationJar.toFile()).equals("jar")) {
                    client.createProjectFrom(projectJar, destinationJar, params[2]);
                } else {
                    LOG.log(Level.WARNING, "New project jar file has to have jar extension");
                }


            } catch (IllegalArgumentException e) {
                LOG.log(Level.WARNING, "Incorrect path");
            }
        } else {
            LOG.log(Level.INFO, "Expected parameters: 2");
            LOG.log(Level.INFO, "1: Path to project jar file");
            LOG.log(Level.INFO, "1: Path where new project will be created");
            LOG.log(Level.INFO, "2: New project name");
        }
    }

    public void setDownloadDir(String[] params) {
        if (checkParamNum(1, params)) {
            client.setDownloadDir(params[0]);
        } else {
            LOG.log(Level.INFO, "Expected parameters: 1");
            LOG.log(Level.INFO, "1: new download dir");
        }
    }

    public void getUploadDir(String[] params) {
        if (checkParamNum(0, params)) {
            LOG.log(Level.INFO, "Upload dir is set to : {0}", client.getUploadDir());
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void setUploadDir(String[] params) {
        if (checkParamNum(1, params)) {
            client.setUploadDir(params[0]);
        } else {
            LOG.log(Level.INFO, "Expected parameters: 1");
            LOG.log(Level.INFO, "1: new upload dir");
        }
    }

    public void getDownloadDir(String[] params) {
        if (checkParamNum(0, params)) {
            LOG.log(Level.INFO, "Download dir is set to : {0}", client.getDownloadDir());
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void setServerAddress(String[] params) {
        if (checkParamNum(1, params)) {
            try {
                client.setServerIPPort(params[0]);
            } catch (UnknownHostException e) {
                LOG.log(Level.WARNING, "Not correct host or IP address: {0}", e.getMessage());
            } catch (IllegalArgumentException e) {
                LOG.log(Level.WARNING, "Port number has to be between 1 - 65535");
            }
        } else {
            LOG.log(Level.INFO, "Expected parameters: 1");
            LOG.log(Level.INFO, "1: Server new IP address[:port]");
        }
    }

    public void getServerAddress(String[] params) {
        if (checkParamNum(0, params)) {
            LOG.log(Level.INFO, "Server address is set to : {0}", client.getServerAddress());
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void setServerPort(String[] params) {
        if (checkParamNum(1, params)) {
            try {
                client.setServerPort(Integer.parseInt(params[0]));
            } catch (IllegalArgumentException e) {
                LOG.log(Level.WARNING, "Port number has to be integer between 1 - 65535");
            }
        } else {
            LOG.log(Level.INFO, "Expected parameters: 1");
            LOG.log(Level.INFO, "1: Server new port");
        }
    }

    public void getServerPort(String[] params) {
        if (checkParamNum(0, params)) {
            LOG.log(Level.INFO, "Server port is set to : {0}", client.getServerPort());
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void setName(String[] params) {
        if (checkParamNum(1, params)) {
            client.setClientName(params[0]);
        } else {
            LOG.log(Level.INFO, "Expected parameters: 1");
            LOG.log(Level.INFO, "1: Client new id");
        }
    }

    public void getName(String[] params) {
        if (checkParamNum(0, params)) {
            LOG.log(Level.INFO, "Client name is set to: {0}", client.getClientName());
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void setMemory(String[] params) {
        if (checkParamNum(1, params)) {
            try {
                client.setMemory(Integer.parseInt(params[0]));
            } catch (NumberFormatException e) {
                LOG.log(Level.WARNING, "Amount of memory allowed to use by tasks has to be positive integer");
            }
        } else {
            LOG.log(Level.INFO, "Expected parameters: 1");
            LOG.log(Level.INFO, "1: new memory limit for task computation");
        }
    }

    public void getMemory(String[] params) {
        if (checkParamNum(0, params)) {
            LOG.log(Level.INFO, "Amount of memory allowed to use by tasks is set to: {0}", client.getMemory());
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void setCores(String[] params) {
        if (checkParamNum(1, params)) {
            try {
                client.setCores(Integer.parseInt(params[0]));
            } catch (NumberFormatException e) {
                LOG.log(Level.WARNING, "Number of cores allowed to use by tasks has to be positive integer");
            }
        } else {
            LOG.log(Level.INFO, "Expected parameters: 1");
            LOG.log(Level.INFO, "1: new limit for cores allowed to use by task computation");
        }
    }

    public void getCores(String[] params) {
        if (checkParamNum(0, params)) {
            LOG.log(Level.INFO, "Number of cores allowed to use by tasks is set to: {0}", client.getCores());
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void getInfo(String[] params) {
        if (checkParamNum(0, params)) {
            getName(params);
            getServerAddress(params);
            getServerPort(params);
            getDownloadDir(params);
            getUploadDir(params);
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void printProjectInfo(String[] params) {
        if (checkParamNum(1, params)) {
            client.getStandartRemoteProvider().printProjectInfo(params[0]);
        } else {
            LOG.log(Level.INFO, "Expected parameters: 1");
            LOG.log(Level.INFO, "1: Project name");
        }
    }

    public void startCalculation(String[] params) {
        if (checkParamNum(0, params)) {
            client.startRecievingTasks();
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void stopCalculation(String[] params) {
        if (checkParamNum(0, params)) {
            client.stopRecievingTasks(false);
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void connect(String[] params) {
        if (params.length == 0) {
            client.connect();
        } else {
            int errNum = 0;
            try {
                if (params.length == 1) {
                    client.setServerIPPort(params[0]);
                } else if (params.length == 2) {
                    client.setServerAddress(params[0]);
                    client.setServerPort(Integer.parseInt(params[1]));
                } else {
                    LOG.log(Level.INFO, "Incorect number of parameter, can be 0, 1 or 2");
                    LOG.log(Level.INFO, "See user documentation");
                }
            } catch (UnknownHostException e) {
                LOG.log(Level.WARNING, "Host or IP address is not valid");
                LOG.log(Level.INFO, " Trying to connect with original address {0}", client.getServerAddress());
                client.connect();
            } catch (IllegalArgumentException e) {
                LOG.log(Level.WARNING, "Port number is not valid");
                LOG.log(Level.INFO, "Trying to connect with original port {0}", client.getServerPort());
                client.connect();
            }

        }

    }

    public void disconnect(String[] params) {
        if (checkParamNum(0, params)) {
            client.disconnect();
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }

    public void auto(String[] params) {
        if (checkParamNum(1, params)) {
            LOG.log(Level.INFO, "Starting automatic proccessing");
            try {
                Path projectJar = client.getUploadFileLocation(params[0]);
                client.startAutoProccess(projectJar);
            } catch (IllegalArgumentException e) {
                LOG.log(Level.WARNING, "Incorrect path");
            }
        } else {
            LOG.log(Level.INFO, "Expected parameters: 1");
            LOG.log(Level.INFO, "1: Path to project jar file");
        }
    }

    public void list(String[] params) {
        if (client.isConnected()) {
            if (checkParamNum(1, params)) {

                switch (params[0]) {
                    case "all":
                        client.getStandartRemoteProvider().printAllProjects();
                        break;
                    case "active":
                        client.getStandartRemoteProvider().printProjects(ProjectState.ACTIVE);
                        break;
                    case "paused":
                        client.getStandartRemoteProvider().printProjects(ProjectState.PAUSED);
                        break;
                    case "completed":
                        client.getStandartRemoteProvider().printProjects(ProjectState.COMPLETED);
                        break;
                    default:
                        LOG.log(Level.INFO, "states which can listed are: all, completed, paused, active");
                }
            } else {
                LOG.log(Level.INFO, "Expected parameters: 1");
                LOG.log(Level.INFO, "1: Type of projects - all, completed, paused, active");
            }
        } else {
            LOG.log(Level.WARNING, "Not connected");
        }
    }

    public void pause(String[] params) {
        if (client.isConnected()) {
            if (checkParamNum(1, params)) {
                client.getStandartRemoteProvider().pauseProject(params[0]);
            } else {
                LOG.log(Level.INFO, "Expected parameters: 1");
                LOG.log(Level.INFO, "1: Name of project which should be paused");
            }
        } else {
            LOG.log(Level.WARNING, "Not connected");
        }
    }

    public void cancel(String[] params) {
        if (client.isConnected()) {
            if (checkParamNum(1, params)) {
                client.getStandartRemoteProvider().cancelProject(params[0]);
            } else {
                LOG.log(Level.INFO, "Expected parameters: 1");
                LOG.log(Level.INFO, "1: Name of project which should be canceled");
            }
        } else {
            LOG.log(Level.WARNING, "Not connected");
        }
    }

    public void resume(String[] params) {
        if (client.isConnected()) {
            if (checkParamNum(1, params)) {
                client.getStandartRemoteProvider().resumeProject(params[0]);
            } else {
                LOG.log(Level.INFO, "Expected parameters: 1");
                LOG.log(Level.INFO, "1: Name of project which should be unpaused");
            }
        } else {
            LOG.log(Level.WARNING, "Not connected");
        }
    }

    public void downloadReady(String[] params) {
        if (client.isConnected()) {
            if (checkParamNum(1, params)) {
                client.getStandartRemoteProvider().isProjectReadyForDownload(params[0]);
            } else {
                LOG.log(Level.INFO, "Expected parameters: 1");
                LOG.log(Level.INFO, "1: Name of project which should be checked if is ready for download");
            }
        } else {
            LOG.log(Level.WARNING, "Not connected");
        }
    }

    public void upload(String[] params) {
        if (client.isConnected()) {
            if (checkParamNum(2, params)) {
                try {
                    Path projectJar = client.getUploadFileLocation(params[0]);
                    Path projectData = client.getUploadFileLocation(params[1]);
                    client.getStandartRemoteProvider().uploadProject(projectJar, projectData);
                } catch (IllegalArgumentException e) {
                    LOG.log(Level.WARNING, "Incorrect path: {0}", e.getMessage());
                }

            } else {
                LOG.log(Level.INFO, "Expected parameters: 2");
                LOG.log(Level.INFO, "1: Path to project jar");
                LOG.log(Level.INFO, "2: Path to project data");
            }
        } else {
            LOG.log(Level.WARNING, "Not connected");
        }
    }

    public void download(String[] params) {
        if (client.isConnected()) {
            if (checkParamNum(1, params)) {
                client.getStandartRemoteProvider().download(params[0], new File(client.getDownloadDir(), params[0] + ".zip"));
            } else {
                LOG.log(Level.INFO, "Expected parameters: 1");
                LOG.log(Level.INFO, "1: Project name");
            }
        } else {
            LOG.log(Level.WARNING, "Not connected");
        }
    }

    public void exit(String[] params) {
        if (checkParamNum(0, params)) {
            client.exitClient();
        } else {
            LOG.log(Level.INFO, "Command has no parameters");
        }
    }
}
