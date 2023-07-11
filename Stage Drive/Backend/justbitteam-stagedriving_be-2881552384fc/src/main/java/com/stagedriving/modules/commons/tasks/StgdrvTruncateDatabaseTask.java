package com.stagedriving.modules.commons.tasks;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;
import java.sql.*;

public class StgdrvTruncateDatabaseTask extends Task {

    public static final String COMMAND_CLEAN_RAW = "cleanraw";
    public static final String COMMAND_CLEAN_HB = "cleanhb";

    private final Runtime runtime;

    private static Connection connect;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    private final int nLocalPort = 3306;
    private final String strDbUser = "rest";
    private final String strDbPassword = "rest";

    public StgdrvTruncateDatabaseTask() {
        this(Runtime.getRuntime());
    }

    public StgdrvTruncateDatabaseTask(Runtime runtime) {
        super("maintenance_task");
        this.runtime = runtime;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {

        ImmutableCollection<String> command = parameters.get("command");

        if (output != null) {
            output.append("Commands: " + command.toString());
        }

        ImmutableList<String> commands = command.asList();
        if (commands.get(0).equals(COMMAND_CLEAN_RAW)) {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:" + nLocalPort + "/stgdrv", strDbUser, strDbPassword);
            statement = connect.createStatement();

            resultSet = statement.executeQuery("SET FOREIGN_KEY_CHECKS=0");

            preparedStatement = connect.prepareStatement("delete from stgdrv.account");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.account_device");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.account_connection");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.account_image");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.account_meta");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.account_group");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.account_has_group");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.event");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.event_image");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.booking");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.event_has_booking");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.checkin");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.event_has_checkin");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.catalog");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.event_has_catalog");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.event_stuff");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.item");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.catalog_has_item");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.post_target");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.event_prize");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.notification");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.notification_has_meta");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.notification_meta");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.notification_has_to");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.notification_has_from");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.fellowship");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.event_has_fellowship");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.brand");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.event_has_buyer");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.item_category");
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from stgdrv.item_family");
            preparedStatement.executeUpdate();

            resultSet = statement.executeQuery("SET FOREIGN_KEY_CHECKS=1");

        } else if (commands.get(0).equals(COMMAND_CLEAN_HB)) {

        }
    }
}
