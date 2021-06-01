## Rollback Flyway

Undo the last migration using the command line:

```
./flyway -pro undo
```

After the command has run successfully, we can check the status of the migrations again:

```
./flyway -pro info
```

Which gives us the following output:

```
+-----------+---------+-------------------+----------+---------------------+---------+----------+
| Category  | Version | Description       | Type     | Installed On        | State   | Undoable |
+-----------+---------+-------------------+----------+---------------------+---------+----------+
| Versioned | 1.0     | create book table | SQL      | 2020-08-22 15:48:00 | Undone  |          |
| Undo      | 1.0     | create book table | UNDO_SQL | 2020-08-22 15:49:47 | Success |          |
| Versioned | 1.0     | create book table | SQL      |                     | Pending | Yes      |
+-----------+---------+-------------------+----------+---------------------+---------+----------+
```
Notice how the undo has been successful, and the first migration is back to pending. Also, in contrast to the first method, the audit trail clearly shows the migrations that were rolled back.

Although Flyway Undo can be useful, it assumes that the whole migration has succeeded. For example, it may not work as expected if a migration fails partway through.