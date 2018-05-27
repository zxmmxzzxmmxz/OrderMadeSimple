# Make sure the Apt package lists are up to date, so we're downloading versions that exist.
cookbook_file "apt-sources.list" do
  path "/etc/apt/sources.list"
end
execute 'apt_update' do
  command 'apt-get update'
end

# Base configuration recipe in Chef.
package "wget"
package "ntp"
package "nginx"
package "postgresql"

cookbook_file "ntp.conf" do
  path "/etc/ntp.conf"
end

cookbook_file "nginx.default" do
    path "/etc/nginx/sites-available/default"
end

execute 'ntp_restart' do
  command 'service ntp restart'
end

execute 'nginx_restart' do
  command 'sudo systemctl restart nginx'
end

execute 'postgresql_create' do
	command 'echo "CREATE DATABASE mydb; CREATE USER vagrant; GRANT ALL PRIVILEGES ON DATABASE mydb TO vagrant;" | sudo -u postgres psql'
end
