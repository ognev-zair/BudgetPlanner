package models;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;

interface Model {
    String tableName = "users";
    String[] attributes = {"id"};
}
