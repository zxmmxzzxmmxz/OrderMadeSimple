execute 'apply_db_scripts_clear_data' do
  command 'psql -h localhost -d projectdb -U projectuser -a -w -f /vagrant/chef/cookbooks/conf/files/database/clearDB.sql'
  environment(
  	'PGPASSWORD' => 'password'
  )
  ignore_failure true
end

execute 'apply_db_scripts_schema_create' do
  command 'psql -h localhost -d projectdb -U projectuser -a -w -f /vagrant/chef/cookbooks/conf/files/database/createDB.sql'
  environment(
  	'PGPASSWORD' => 'password'
  )
  ignore_failure true
end

execute 'apply_db_scripts_mock_data' do
  command 'psql -h localhost -d projectdb -U projectuser -a -w -f /vagrant/chef/cookbooks/conf/files/database/mockData.sql'
  environment(
  	'PGPASSWORD' => 'password'
  )
  ignore_failure true
end