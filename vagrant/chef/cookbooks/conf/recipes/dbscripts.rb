execute 'apply_db_scripts' do
  command 'psql -h localhost -d projectdb -U projectuser -a -w -f /vagrant/chef/cookbooks/conf/files/database/createDB.sql'
  environment(
  	'PGPASSWORD' => 'password'
  )
  ignore_failure true
end

execute 'apply_db_scripts' do
  command 'psql -h localhost -d projectdb -U projectuser -a -w -f /vagrant/chef/cookbooks/conf/files/database/mockData.sql'
  environment(
  	'PGPASSWORD' => 'password'
  )
  ignore_failure true
end